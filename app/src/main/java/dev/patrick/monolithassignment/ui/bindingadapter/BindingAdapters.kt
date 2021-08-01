package dev.patrick.monolithassignment.ui.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import dev.patrick.monolithassignment.ui.vo.UiDate

@BindingAdapter("monthVisibility")
fun TextView.showMonthVisibility(date: String) {
    visibility = when (date) {
        "1" -> View.VISIBLE
        else -> View.INVISIBLE
    }
}