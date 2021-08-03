package dev.patrick.monolithassignment.ui.reserve

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.patrick.monolithassignment.databinding.ReserveFragmentBinding
import dev.patrick.monolithassignment.ui.adapter.DateAdapter
import dev.patrick.monolithassignment.ui.adapter.TimetableAdapter
import dev.patrick.monolithassignment.ui.adapter.UiDateClickListener
import dev.patrick.monolithassignment.ui.adapter.UiTimetableClickListener
import dev.patrick.monolithassignment.ui.vo.UiDate
import dev.patrick.monolithassignment.ui.vo.UiTimetable
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ReserveFragment : Fragment() {

    @Inject
    lateinit var dateAdapter: DateAdapter

    @Inject
    lateinit var timetableAdapter: TimetableAdapter

    private val viewModel: ReserveViewModel by viewModels()

    private var _binding: ReserveFragmentBinding? = null
    private val binding: ReserveFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReserveFragmentBinding.inflate(inflater, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            rvDate.adapter = dateAdapter
            rvTimetable.adapter = timetableAdapter
            rvDate.setHasFixedSize(true)
        }

        setUiItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiDates.observe(viewLifecycleOwner, Observer {
            dateAdapter.submitList(it)
        })

        viewModel.uiTimetable.observe(viewLifecycleOwner) {
            timetableAdapter.submitList(it)
        }
    }

    private fun setUiItemClickListener() {
        dateAdapter.setUiDateItemClickListener(object : UiDateClickListener {
            override fun onClick(uiDate: UiDate) {
                viewModel.onDateSelect(uiDate)
            }
        })
        timetableAdapter.setUiTimetableClickListener(object : UiTimetableClickListener {
            override fun onClick(uiTimetable: UiTimetable) {
                viewModel.onTimeSelect(uiTimetable)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ReserveFragment()
    }
}