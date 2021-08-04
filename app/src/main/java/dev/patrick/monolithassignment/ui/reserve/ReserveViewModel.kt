package dev.patrick.monolithassignment.ui.reserve

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrick.monolithassignment.domain.usecase.GetScheduleDate
import dev.patrick.monolithassignment.domain.usecase.GetScheduleTimetable
import dev.patrick.monolithassignment.ui.vo.UiDate
import dev.patrick.monolithassignment.ui.vo.UiTimetable
import dev.patrick.monolithassignment.utils.Event
import dev.patrick.monolithassignment.utils.parseKoreanDayOfWeek
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class ReserveViewModel @Inject constructor(
    private val getScheduleDate: GetScheduleDate,
    private val getScheduleTimetable: GetScheduleTimetable
) : ViewModel() {

    private val _uiDates = MediatorLiveData<List<UiDate>>()
    val uiDates: LiveData<List<UiDate>>
        get() = _uiDates

    private val _currentMonth = MutableLiveData<String>()
    val currentMonth: LiveData<String> = _currentMonth

    private val _selectedDate = MutableLiveData<UiDate>()
    val selectedDate: LiveData<UiDate>
        get() = _selectedDate

    private val _selectedTime = MutableLiveData<UiTimetable?>()
    val selectedTime: LiveData<UiTimetable?>
        get() = _selectedTime

    val nextBtnEnabled = _selectedTime.map { it != null }

    private val _resultDateAndTime = MutableLiveData<Event<String>>()
    val resultDateAndTime: LiveData<Event<String>>
        get() = _resultDateAndTime

    private val _uiTimetable = MediatorLiveData<List<UiTimetable>>()
    val uiTimetable: LiveData<List<UiTimetable>>
        get() = _uiTimetable

    init {
        viewModelScope.launch {
            getUiDates()
        }

        _uiDates.addSource(_selectedDate) {
            val selectedIndex = uiDates.value?.indexOfFirst {
                it.month == _selectedDate.value?.month && it.date == _selectedDate.value?.date
            }
            selectedIndex?.let {
                _uiDates.value = uiDates.value?.toMutableList()?.mapIndexed { index, date ->
                    if (index == selectedIndex) {
                        date.copy(selected = true)
                    } else {
                        date.copy(selected = false)
                    }
                }
            }
        }

        _uiTimetable.addSource(_selectedDate) {
            viewModelScope.launch {
                getScheduleTimetable(it.dayOfWeek == DayOfWeek.SUNDAY).map { product ->
                    product.timeList.map { time ->
                        UiTimetable.mapFromDomainTimeList(time)
                    }
                }.collect { timeList ->
                    _uiTimetable.value = timeList
                }
            }
        }

        _uiTimetable.addSource(_selectedTime) {
            val selectedIndex = uiTimetable.value?.indexOfFirst { it.timeSlot == _selectedTime.value?.timeSlot }
            selectedIndex?.let {
                _uiTimetable.value = uiTimetable.value?.toMutableList()?.mapIndexed { index, time ->
                    if (index == selectedIndex) {
                        time.copy(selected = true)
                    } else {
                        time.copy(selected = false)
                    }
                }
            }
        }
    }

    suspend fun getUiDates() {
        _uiDates.value = getScheduleDate().map { UiDate.mapFromDomainDate (it) }
    }

    fun onDateSelect(uiDate: UiDate) {
        if (_selectedDate.value != uiDate) {
            _selectedTime.value = null
        }
        _selectedDate.value = uiDate
        Timber.i("selected date: $uiDate")
    }

    fun onTimeSelect(uiTimetable: UiTimetable) {
        _selectedTime.value = uiTimetable
        Timber.i("selected time: $uiTimetable")
    }

    fun onNextBtnClicked() {
        if (nextBtnEnabled.value == true) {
            _resultDateAndTime.value = Event(getResultDateTime())
        }
    }

    fun onPrevBtnClicked() {
        _selectedTime.value = null
    }

    fun setCurrentMonth(currentMonth: String) {
        _currentMonth.value = currentMonth
    }

    private fun getResultDateTime(): String {
        val date = selectedDate.value?.run {
            "$month ${date}일 (${parseKoreanDayOfWeek(dayOfWeek)})"
        }
        val time = selectedTime.value?.timeSlot
        return "$date $time"
    }
}