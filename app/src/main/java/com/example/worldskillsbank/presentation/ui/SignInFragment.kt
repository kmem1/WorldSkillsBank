package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.worldskillsbank.databinding.FragmentSignInBinding
import com.example.worldskillsbank.presentation.viewmodels.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        binding.atmsButton.setOnClickListener { }

        binding.ratesButton.setOnClickListener { }

        binding.signInButton.setOnClickListener { }

        viewModel.currentEuroRate.observe(viewLifecycleOwner) {
            binding.currentEurTextView.text = it
        }

        viewModel.currentUsdRate.observe(viewLifecycleOwner) {
            binding.currentUsdTextView.text = it
        }

        return binding.root
    }
}