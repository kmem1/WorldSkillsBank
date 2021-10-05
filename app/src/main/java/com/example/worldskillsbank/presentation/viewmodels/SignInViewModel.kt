package com.example.worldskillsbank.presentation.viewmodels

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class SignInViewModel : ViewModel() {

    val currentDate: String
        get() {
            val currentTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            return dateFormat.format(currentTime)
        }

    val currentUsdRate: String
        get() {
            return "80.2"
        }

    val currentEurRate: String
        get() {
            return "75.14"
        }
}