package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsbank.databinding.FragmentBankLocationsBinding
import com.example.worldskillsbank.domain.model.BankLocation
import com.example.worldskillsbank.presentation.adapters.BankLocationsAdapter
import com.example.worldskillsbank.presentation.viewmodels.BankLocationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BankLocationsFragment : Fragment() {

    private var _binding: FragmentBankLocationsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BankLocationsViewModel by viewModels()

    private var bankLocations = ArrayList<BankLocation>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBankLocationsBinding.inflate(inflater, container, false)

        binding.bankLocationsRecyclerView.adapter =
            BankLocationsAdapter(requireContext(), bankLocations, Date())
        binding.bankLocationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.bankLocations.observe(viewLifecycleOwner) { locations ->
            bankLocations.clear()
            bankLocations.addAll(locations)
            binding.bankLocationsRecyclerView.adapter?.notifyDataSetChanged()
        }

        return binding.root
    }

}