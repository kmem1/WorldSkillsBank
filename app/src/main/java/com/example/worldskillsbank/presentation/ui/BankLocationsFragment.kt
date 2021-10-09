package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.worldskillsbank.databinding.FragmentBankLocationsBinding
import com.example.worldskillsbank.databinding.FragmentSignInBinding

class BankLocationsFragment : Fragment() {

    private var _binding: FragmentBankLocationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}