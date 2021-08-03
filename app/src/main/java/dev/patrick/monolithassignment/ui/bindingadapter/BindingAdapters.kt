package dev.patrick.monolithassignment.ui.bindingadapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import dev.patrick.monolithassignment.R
import dev.patrick.monolithassignment.domain.model.StockStatus
import dev.patrick.monolithassignment.ui.vo.UiDate
import timber.log.Timber

@BindingAdapter("monthVisibility")
fun TextView.showMonthVisibility(date: String) {
    visibility = when (date) {
        "1" -> View.VISIBLE
        else -> View.INVISIBLE
    }
}

@BindingAdapter("timetableStatus")
fun TextView.setTimetableStatus(status: StockStatus) {
    text = status.koreanStr
    when (status) {
        StockStatus.SOLDOUT -> {
            setTextColor(Color.DKGRAY)
        }
        StockStatus.RELAXED -> {
            setTextColor(Color.GREEN)
        }
        StockStatus.NORMAL -> {
            setTextColor(Color.BLUE)
        }
        StockStatus.CROWDED -> {
            setTextColor(Color.RED)
        }
    }
}

@BindingAdapter("enableStatus")
fun ViewGroup.setEnableStatus(stockStatus: StockStatus) {
    if (stockStatus == StockStatus.SOLDOUT) {
        setBackgroundColor(Color.LTGRAY)
        isClickable = false
        isFocusable = false
    } else {
        setBackgroundResource(R.drawable.border_time)
        isClickable = true
        isFocusable = true
    }
}
