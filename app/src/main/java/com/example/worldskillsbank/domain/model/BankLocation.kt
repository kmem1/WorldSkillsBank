package com.example.worldskillsbank.domain.model

data class BankLocation(
    val address: String,
    val type: String,
    val openTimeInMinutes: Int,
    val closeTimeInMinutes: Int
)