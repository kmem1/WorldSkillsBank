package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.worldskillsbank.R
import com.example.worldskillsbank.databinding.FragmentSignInBinding
import com.example.worldskillsbank.presentation.viewmodels.SignInViewModel

class SignInFragment : Fragment() {

    var _binding: FragmentSignInBinding? = null
    val binding get() = _binding!!

    val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        binding.atmsButton.setOnClickListener {
            Log.d("qwe", "qwe")
        }

        binding.ratesButton.setOnClickListener {
            Log.d("qwe", viewModel.currentDate)
        }

        binding.signInButton.setOnClickListener {  }

        return binding.root
    }
}