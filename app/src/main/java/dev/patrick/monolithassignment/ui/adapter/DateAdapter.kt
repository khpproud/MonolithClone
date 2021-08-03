package dev.patrick.monolithassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.patrick.monolithassignment.databinding.ItemDateBinding
import dev.patrick.monolithassignment.ui.vo.UiDate
import timber.log.Timber
import javax.inject.Inject

class DateAdapter @Inject constructor() : ListAdapter<UiDate, DateAdapter.DateViewHolder>(
    DATE_DIFF_CALLBACK) {

    private var uiDateItemClickListener: UiDateClickListener? = null
    fun setUiDateItemClickListener(listener: UiDateClickListener) {
        uiDateItemClickListener = listener
    }

    fun getCurrentMonth(position: Int): String {
        val firstItem = getItem(position)
        if (firstItem.date == "1") {
            Timber.w("first date: ${firstItem.month}")
        }
        return firstItem.month
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        uiDateItemClickListener?.let { holder.bind(getItem(position), it) }
    }

    class DateViewHolder private constructor(
        private val binding: ItemDateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiDate, clickListener: UiDateClickListener) {
            binding.clickListener = clickListener
            binding.uiDate = item
            binding.layoutDate.isSelected = item.selected
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): DateViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return DateViewHolder(ItemDateBinding.inflate(inflater, parent, false))
            }
        }
    }

    companion object {
        private val DATE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiDate>() {
            override fun areItemsTheSame(oldItem: UiDate, newItem: UiDate): Boolean {
                return oldItem.month == newItem.month && oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: UiDate, newItem: UiDate): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface UiDateClickListener {
    fun onClick(uiDate: UiDate)
}