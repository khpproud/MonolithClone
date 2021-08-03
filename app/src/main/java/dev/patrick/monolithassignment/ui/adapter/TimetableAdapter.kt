package dev.patrick.monolithassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.patrick.monolithassignment.databinding.ItemTimetableBinding
import dev.patrick.monolithassignment.ui.vo.UiTimetable
import javax.inject.Inject

class TimetableAdapter @Inject constructor()
    : ListAdapter<UiTimetable, TimetableAdapter.TimeViewHolder>(TIME_DIFF_CALLBACK) {

    private var uiTimetableClickListener: UiTimetableClickListener? = null
    fun setUiTimetableClickListener(listener: UiTimetableClickListener) {
        uiTimetableClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        uiTimetableClickListener?.let {
            holder.bind(getItem(position), it)
        }
    }

    class TimeViewHolder private constructor(
        private val binding: ItemTimetableBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiTimetable, listener: UiTimetableClickListener) {
            binding.clickListener = listener
            binding.uiTimetable = item
            binding.layoutTime.isSelected = item.selected
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): TimeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return TimeViewHolder(ItemTimetableBinding.inflate(inflater, parent, false))
            }
        }
    }

    companion object {
        private val TIME_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiTimetable>() {
            override fun areItemsTheSame(oldItem: UiTimetable, newItem: UiTimetable): Boolean {
                return oldItem.timeSlot == newItem.timeSlot
            }

            override fun areContentsTheSame(oldItem: UiTimetable, newItem: UiTimetable): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface UiTimetableClickListener {
    fun onClick(uiTimetable: UiTimetable)
}