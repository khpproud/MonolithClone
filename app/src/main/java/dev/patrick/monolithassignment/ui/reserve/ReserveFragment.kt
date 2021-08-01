package dev.patrick.monolithassignment.ui.reserve

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dev.patrick.monolithassignment.databinding.ReserveFragmentBinding
import dev.patrick.monolithassignment.ui.adapter.DateAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ReserveFragment : Fragment() {

    @Inject
    lateinit var dateAdapter: DateAdapter

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
            rvDate.setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiDates.observe(viewLifecycleOwner, Observer {
            dateAdapter.submitList(it)
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