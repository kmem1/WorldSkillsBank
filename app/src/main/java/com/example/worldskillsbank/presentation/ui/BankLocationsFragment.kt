package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsbank.databinding.FragmentBankLocationsBinding
import com.example.worldskillsbank.databinding.FragmentSignInBinding
import com.example.worldskillsbank.domain.model.BankLocation
import com.example.worldskillsbank.presentation.adapters.BankLocationsAdapter
import java.util.*

class BankLocationsFragment : Fragment() {

    private var _binding: FragmentBankLocationsBinding? = null
    private val binding get() = _binding!!

    val bankLocations = arrayListOf(
        BankLocation("Address nice address ya znay", "Банкомат", 0, 0),
        BankLocation("Address nice addre", "Отделение", 0, 5*60),
        BankLocation("Address nice address znay", "Банкомат", 0, 22*60),
        BankLocation("Address nice ddressznay", "Банкомат", 13*60, 22*60)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBankLocationsBinding.inflate(inflater, container, false)

        binding.bankLocationsRecyclerView.adapter = BankLocationsAdapter(requireContext(), bankLocations, Date())
        binding.bankLocationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }
}