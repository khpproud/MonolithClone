package dev.patrick.monolithassignment.ui.reserve

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.patrick.monolithassignment.databinding.FooterButtonsBinding
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

    private var _footerBinding: FooterButtonsBinding? = null
    private val footerBinding: FooterButtonsBinding
        get() = _footerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReserveFragmentBinding.inflate(inflater, container, false)
        _footerBinding = binding.footer
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            rvDate.adapter = dateAdapter
            rvTimetable.adapter = timetableAdapter
            rvDate.setHasFixedSize(true)
        }
        footerBinding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ReserveFragment.viewModel
        }

        setUiItemClickListener()
        setScrollListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiDates.observe(viewLifecycleOwner, Observer {
            dateAdapter.submitList(it)
        })

        viewModel.uiTimetable.observe(viewLifecycleOwner) {
            timetableAdapter.submitList(it)
            binding.rvTimetable.smoothScrollToPosition(0)
        }

        viewModel.nextBtnEnabled.observe(viewLifecycleOwner) {
            footerBinding.btnNext.isEnabled = it
        }
        viewModel.resultDateAndTime.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                Snackbar.make(requireView(), result, Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok) {
                }.show()
            }
        }

        viewModel.currentMonth.observe(viewLifecycleOwner) {
            binding.tvCurrentMonth.text = it
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

    private fun setScrollListener() {
        binding.rvDate.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstCompletelyVisiblePosition =
                    (binding.rvDate.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val currentMonth = dateAdapter.getCurrentMonth(firstCompletelyVisiblePosition)
                if (currentMonth != viewModel.currentMonth.value) {
                    viewModel.setCurrentMonth(currentMonth)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _footerBinding = null
    }

    companion object {
        fun newInstance() = ReserveFragment()
    }
}