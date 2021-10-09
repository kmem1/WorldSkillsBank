package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.worldskillsbank.databinding.FragmentBankLocationsBinding
import com.example.worldskillsbank.databinding.FragmentSignInBinding
import com.example.worldskillsbank.domain.model.BankLocation

class BankLocationsFragment : Fragment() {

    private var _binding: FragmentBankLocationsBinding? = null
    private val binding get() = _binding!!

    val bankLocations = arrayOf(
        BankLocation("Address nice address ya znay", "Банкомат", 0, 0)

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBankLocationsBinding.inflate(inflater, container, false)



        return binding.root
    }
}