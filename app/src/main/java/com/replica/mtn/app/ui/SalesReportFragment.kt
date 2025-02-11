package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.SaleReportFragmentBinding
import com.replica.mtn.app.utils.Loader
import java.text.SimpleDateFormat
import java.util.Locale


class SalesReportFragment : Fragment() {
    private var loader: Loader? = null
    private var _binding: SaleReportFragmentBinding? = null
    private val binding get() = _binding!!
    private var validDateFrom = ""
    private var validDateTo = ""
    private var selectedItem = ""
    private var data = "All"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SaleReportFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = Loader(requireContext())
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
        binding.reType.setOnClickListener {
            showCustomDialog()
        }
        binding.data.setOnClickListener {
            binding.reType.performClick()
        }

        // Load data from string array
        val items = resources.getStringArray(R.array.sales_report)

        // Create an ArrayAdapter
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, items)

        // Set the adapter to the Spinner
        binding.spGraph.adapter = adapter
        binding.spGraph.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        binding.btnLogin.setOnClickListener {
            loader?.start()
            Handler(Looper.getMainLooper()).postDelayed({
                loader?.stop()
                findNavController().navigate(
                    SalesReportFragmentDirections.actionSalesReportToSalesReportSecond(
                        validDateFrom,validDateTo,true
                    )
                )
            },1000)
        }
    }
    private fun showCustomDialog() {
        // Create the dialog
        val dialog = Dialog(requireContext())

        // Inflate the custom layout
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_dialog_transaction, null)

        // Set the custom layout to the dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)

        // Make the dialog's background transparent if necessary
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._250sdp), // Change as needed
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._300sdp)  // Change as needed
        )

        // Set click listener for the close button
        val chkAll: CheckBox = view.findViewById(R.id.chk1)
        val chk2: CheckBox = view.findViewById(R.id.chk2)
        val chk3: CheckBox = view.findViewById(R.id.chk3)
        val chk4: CheckBox = view.findViewById(R.id.chk4)
        val chk5: CheckBox = view.findViewById(R.id.chk5)
        val btnSend: TextView = view.findViewById(R.id.btnSend)
        val closeButton: TextView = view.findViewById(R.id.btnCancel)
        chkAll.isChecked = true
        chk2.isChecked = true
        chk3.isChecked = true
        chk4.isChecked = true
        chk5.isChecked = true
        chkAll.setOnCheckedChangeListener { compoundButton, _ ->
            if(compoundButton.isChecked){
                chk2.isChecked =true
                chk3.isChecked =true
                chk4.isChecked =true
                chk5.isChecked =true
                data = "All"
            }else {
                data = ""
                chk2.isChecked =false
                chk3.isChecked =false
                chk4.isChecked =false
                chk5.isChecked =false
            }
        }

        closeButton.setOnClickListener {
            dialog.dismiss()
        }
        btnSend.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}