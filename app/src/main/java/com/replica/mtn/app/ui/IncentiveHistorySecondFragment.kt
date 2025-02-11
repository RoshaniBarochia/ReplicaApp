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
import com.replica.mtn.app.databinding.IncentiveHistorySecondFragmentBinding
import com.replica.mtn.app.databinding.TransactionHistoryFragmentBinding
import java.text.SimpleDateFormat
import java.util.Locale

class IncentiveHistorySecondFragment : Fragment() {

    private var _binding: IncentiveHistorySecondFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = IncentiveHistorySecondFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}