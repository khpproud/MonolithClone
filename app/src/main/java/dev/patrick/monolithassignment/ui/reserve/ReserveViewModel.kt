package dev.patrick.monolithassignment.ui.reserve

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrick.monolithassignment.domain.usecase.GetScheduleDate
import dev.patrick.monolithassignment.ui.vo.UiDate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveViewModel @Inject constructor(
    private val getScheduleDate: GetScheduleDate
) : ViewModel() {

    private val _uiDates = MutableLiveData<List<UiDate>>()
    val uiDates: LiveData<List<UiDate>>
        get() = _uiDates

    private val _currentMonth = MutableLiveData<String>()
    val currentMonth: LiveData<String> = _currentMonth

    init {
        viewModelScope.launch {
            getUiDates()
        }
    }

    suspend fun getUiDates() {
        _uiDates.value = getScheduleDate().map { UiDate.mapFromDomainDate (it) }
    }
}