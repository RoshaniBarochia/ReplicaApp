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
import com.replica.mtn.app.databinding.SalesReportSecondFragmentBinding
import com.replica.mtn.app.databinding.TransactionHistorySecondFragmentBinding

class SalesReportSecondFragment : Fragment() {

    private var _binding: SalesReportSecondFragmentBinding? = null
    private val binding get() = _binding!!
    private var validDateFrom: String? = ""
    private var validDateTo: String? = ""
    private var isFrom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: SalesReportSecondFragmentArgs by navArgs()
        validDateFrom = args.fromDate
        validDateTo = args.toDate
        isFrom = args.isFrom
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SalesReportSecondFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isFrom)
            binding.selectCnt.text = getString(R.string.sales_report_from_s_to_s,validDateFrom,validDateTo)
        else {
            binding.selectCnt.text =
                getString(R.string.deposit_history_from_s_to_s, validDateFrom, validDateTo)
            binding.tvNo.text = "0"
            binding.tvZar.text = getString(R.string._0_00_zar)
            binding.tvZarE.text = getString(R.string._0_00_zar)
        }

        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSkip.setOnClickListener {
            findNavController().navigate(SalesReportSecondFragmentDirections.actionSalesReportSecondToSalesReportThird(validDateFrom,validDateTo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}