package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.replica.mtn.app.databinding.IncentiveHistoryFragmentBinding
import com.replica.mtn.app.databinding.TransactionHistoryFragmentBinding
import java.text.SimpleDateFormat
import java.util.Locale

class IncentiveHistoryFragment : Fragment() {

    private var _binding: IncentiveHistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private var validDateFrom = ""
    private var validDateTo = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = IncentiveHistoryFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFromDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }

                private fun updateDateInView() {
                    val myFormat = "dd-MMM, yyyy" // mention the format you need
                    val myFormat1 = "dd-MM-yy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val sdf1 = SimpleDateFormat(myFormat1, Locale.US)
                    binding.tvFromDate.text = sdf1.format(cal.time)
                    validDateFrom = sdf.format(cal.time).toString()
                }
            }

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                dateSetListener,
                year,
                month,
                dayOfMonth
            )

            // Set Min Date (7-Jan-2025)
            val minDate = Calendar.getInstance().apply {
                set(2025, Calendar.JANUARY, 7)
            }.timeInMillis
            datePickerDialog.datePicker.minDate = minDate

            // Set Max Date (22-Jan-2025)
            val maxDate = Calendar.getInstance().apply {
                set(2025, Calendar.JANUARY, 22)
            }.timeInMillis
            datePickerDialog.datePicker.maxDate = maxDate


            datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                // Perform desired actions when the cancel button is clicked
                println("Cancel button clicked")
                binding.tvFromDate.text = ""
            }
            datePickerDialog.setOnCancelListener {
                binding.tvFromDate.text = ""
            }
            datePickerDialog.show()
        }

        binding.tvToDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }

                private fun updateDateInView() {
                    val myFormat = "dd-MMM, yyyy" // mention the format you need
                    val myFormat1 = "dd-MM-yy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val sdf1 = SimpleDateFormat(myFormat1, Locale.US)
                    binding.tvToDate.text = sdf1.format(cal.time)
                    validDateTo = sdf.format(cal.time).toString()
                }
            }

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                dateSetListener,
                year,
                month,
                dayOfMonth
            )

            // Set Min Date (7-Jan-2025)
            val minDate = Calendar.getInstance().apply {
                set(2025, Calendar.JANUARY, 7)
            }.timeInMillis
            datePickerDialog.datePicker.minDate = minDate

            // Set Max Date (22-Jan-2025)
            val maxDate = Calendar.getInstance().apply {
                set(2025, Calendar.JANUARY, 22)
            }.timeInMillis
            datePickerDialog.datePicker.maxDate = maxDate


            datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                // Perform desired actions when the cancel button is clicked
                println("Cancel button clicked")
                binding.tvToDate.text = ""
            }
            datePickerDialog.setOnCancelListener {
                binding.tvToDate.text = ""
            }
            datePickerDialog.show()
        }
        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(
                IncentiveHistoryFragmentDirections.actionIncentiveHistoryToIncentiveHistorySecond()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}