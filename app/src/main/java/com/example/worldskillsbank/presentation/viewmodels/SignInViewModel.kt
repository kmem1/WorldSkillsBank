package com.example.worldskillsbank.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskillsbank.common.State
import com.example.worldskillsbank.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val currentDate: String
        get() {
            val currentTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            return dateFormat.format(currentTime)
        }

    private val _currentUsdRate: MutableLiveData<String> = MutableLiveData("0.00")
    val currentUsdRate: LiveData<String> = _currentUsdRate

    private val _currentEuroRate: MutableLiveData<String> = MutableLiveData("0.00")
    val currentEuroRate: LiveData<String> = _currentEuroRate

    init {
        viewModelScope.launch {
            repository.getValuteByCharCode(EUR_CHARCODE, Date()).collect {
                if (it is State.Success) {
                    _currentEuroRate.value = "%.2f".format(it.data?.value)
                }
            }
        }
        viewModelScope.launch {
            repository.getValuteByCharCode(USD_CHARCODE, Date()).collect {
                if (it is State.Success) {
                    _currentUsdRate.value = "%.2f".format(it.data?.value)
                }
            }
        }
    }



    companion object {
        private const val EUR_CHARCODE = "EUR"
        private const val USD_CHARCODE = "USD"
    }
}