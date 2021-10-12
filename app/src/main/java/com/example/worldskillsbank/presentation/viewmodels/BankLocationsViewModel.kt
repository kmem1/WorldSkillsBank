package com.example.worldskillsbank.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskillsbank.domain.model.BankLocation
import com.example.worldskillsbank.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankLocationsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _bankLocations = MutableLiveData<ArrayList<BankLocation>>()
    val bankLocations: LiveData<ArrayList<BankLocation>> = _bankLocations

    init {
        viewModelScope.launch {
            _bankLocations.value = repository.getBankLocations()
        }
    }
}