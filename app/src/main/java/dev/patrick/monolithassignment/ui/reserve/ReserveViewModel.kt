package dev.patrick.monolithassignment.ui.reserve

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrick.monolithassignment.domain.usecase.GetScheduleDate
import dev.patrick.monolithassignment.domain.usecase.GetScheduleTimetable
import dev.patrick.monolithassignment.ui.vo.UiDate
import dev.patrick.monolithassignment.ui.vo.UiTimetable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReserveViewModel @Inject constructor(
    private val getScheduleDate: GetScheduleDate,
    private val getScheduleTimetable: GetScheduleTimetable
) : ViewModel() {

    private val _uiDates = MutableLiveData<List<UiDate>>()
    val uiDates: LiveData<List<UiDate>>
        get() = _uiDates

    private val _currentMonth = MutableLiveData<String>()
    val currentMonth: LiveData<String> = _currentMonth

    private val _selectedDate = MutableLiveData<UiDate>()
    val selectedDate: LiveData<UiDate>
        get() = _selectedDate

    private val _selectedTime = MutableLiveData<UiTimetable>()
    val selectedTime: LiveData<UiTimetable>
        get() = _selectedTime

    val uiTimetable = MediatorLiveData<List<UiTimetable>>()

    init {
        viewModelScope.launch {
            getUiDates()
        }

        uiTimetable.addSource(_selectedDate) {
            viewModelScope.launch {
                getScheduleTimetable(it.dayOfWeek == "일").map { product ->
                    product.timeList.map { time ->
                        UiTimetable.mapFromDomainTimeList(time)
                    }
                }.collect { timeList ->
                    uiTimetable.value = timeList
                }
            }
        }

        uiTimetable.addSource(_selectedTime) {
            val selectedIndex = uiTimetable.value?.indexOfFirst { it.timeSlot == _selectedTime.value?.timeSlot }
            selectedIndex?.let {
                uiTimetable.value = uiTimetable.value?.toMutableList()?.mapIndexed { index, time ->
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
        _selectedDate.value = uiDate
        Timber.i("selected date: $uiDate")
    }

    fun onTimeSelect(uiTimetable: UiTimetable) {
        _selectedTime.value = uiTimetable
        Timber.i("selected time: $uiTimetable")
    }
}