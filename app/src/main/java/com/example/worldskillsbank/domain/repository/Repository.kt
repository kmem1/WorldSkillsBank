package com.example.worldskillsbank.domain.repository

import android.content.Context
import com.example.worldskillsbank.common.State
import com.example.worldskillsbank.domain.model.Valute
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

    fun getValuteByCharCode(charCode: String, date: Date): Flow<State<Valute?>>
}