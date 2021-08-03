package dev.patrick.monolithassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.patrick.monolithassignment.databinding.ItemDateBinding
import dev.patrick.monolithassignment.ui.vo.UiDate
import javax.inject.Inject

class DateAdapter @Inject constructor() : ListAdapter<UiDate, DateAdapter.DateViewHolder>(
    DATE_DIFF_CALLBACK) {

    private var uiDateItemClickListener: UiDateClickListener? = null
    fun setUiDateItemClickListener(listener: UiDateClickListener) {
        uiDateItemClickListener = listener
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
            binding.uiDate = item
            binding.clickListener = clickListener
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