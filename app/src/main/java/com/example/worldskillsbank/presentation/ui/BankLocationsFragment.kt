package com.example.worldskillsbank.presentation.ui

import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsbank.databinding.FragmentBankLocationsBinding
import com.example.worldskillsbank.databinding.FragmentSignInBinding
import com.example.worldskillsbank.domain.model.BankLocation
import com.example.worldskillsbank.presentation.adapters.BankLocationsAdapter
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

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

        val bankLocations = ArrayList<BankLocation>()

        val ins = resources.openRawResource(
            resources.getIdentifier("atms", "raw", requireActivity().packageName))

        val reader = JsonReader(InputStreamReader(ins, "UTF8"))
        try {
            reader.beginObject()
            reader.nextName()
            reader.beginArray()
            while (reader.hasNext()) {
                bankLocations.add(readBankLocation(reader))
            }
            reader.endArray()
            reader.endObject()
            Log.d("qwe", bankLocations.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }

    private fun readBankLocation(reader: JsonReader): BankLocation {
        var address: String = ""
        var type: String = ""
        var openTimeInMinutes: Int = 0
        var closeTimeInMinutes: Int = 0

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "address" -> address = reader.nextString()
                "type" -> type = reader.nextString()
                "openTimeInMinutes" -> openTimeInMinutes = reader.nextInt()
                "closeTimeInMinutes" -> closeTimeInMinutes = reader.nextInt()
            }
        }
        reader.endObject()

        return BankLocation(address, type, openTimeInMinutes, closeTimeInMinutes)
    }
}