package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.replica.mtn.app.R
import com.replica.mtn.app.adapter.TransactionHistoryAdapter
import com.replica.mtn.app.databinding.TransactionHistorySecondFragmentBinding

class TransactionHistorySecondFragment : Fragment() {

    private var _binding: TransactionHistorySecondFragmentBinding? = null
    private val binding get() = _binding!!
    private var validDateFrom: String? = ""
    private var validDateTo: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: TransactionHistorySecondFragmentArgs by navArgs()
        validDateFrom = args.fromDate
        validDateTo = args.toDate
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TransactionHistorySecondFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectCnt.text = getString(R.string.transaction_history_from_s_to_s,validDateFrom,validDateTo)
        binding.recyclerView.adapter = TransactionHistoryAdapter(requireContext())
        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}