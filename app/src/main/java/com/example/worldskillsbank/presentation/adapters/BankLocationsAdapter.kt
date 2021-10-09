package com.example.worldskillsbank.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsbank.R
import com.example.worldskillsbank.databinding.BankLocationItemBinding
import com.example.worldskillsbank.domain.model.BankLocation
import java.text.SimpleDateFormat
import java.util.*

class BankLocationsAdapter(
    private val context: Context,
    private val bankLocations: ArrayList<BankLocation>,
    private val currentTime: Date
) :
    RecyclerView.Adapter<BankLocationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.bank_location_item, parent, false)
        val binding = BankLocationItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bankLocations[position], position)
    }

    override fun getItemCount(): Int = bankLocations.size

    inner class ViewHolder(private val binding: BankLocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BankLocation, position: Int) {
            binding.addressView.text = item.address
            binding.typeView.text = item.type

            val openTimeHour = item.openTimeInMinutes / 60
            val openTimeMinute = item.openTimeInMinutes % 60
            val closeTimeHour = item.closeTimeInMinutes / 60
            val closeTimeMinute = item.closeTimeInMinutes % 60

            val cal = Calendar.getInstance().apply { time = currentTime }
            val currentTimeInMinutes = cal.get(Calendar.HOUR) * 60 + cal.get(Calendar.MINUTE)

            if (currentTimeInMinutes in item.openTimeInMinutes..item.closeTimeInMinutes) {
                binding.isWorkingView.text = context.getString(R.string.open)
                binding.isWorkingView.setTextColor(context.resources.getColor(R.color.green, null))
            } else {
                binding.isWorkingView.text = context.getString(R.string.closed)
                binding.isWorkingView.setTextColor(context.resources.getColor(R.color.red, null))
            }

            val openTimeString = Pair(openTimeHour, openTimeMinute).toTimeFormat()
            val closeTimeString = Pair(closeTimeHour, closeTimeMinute).toTimeFormat()
            binding.workHoursView.text = context.getString(
                R.string.workTimeFormat, openTimeString, closeTimeString)
        }

        private fun Pair<Int, Int>.toTimeFormat() : String {
            val hours = if (first < 10) "0$first" else "$first"
            val minutes = if (second < 10) "0$second" else "$second"
            return "$hours:$minutes"
        }
    }
}