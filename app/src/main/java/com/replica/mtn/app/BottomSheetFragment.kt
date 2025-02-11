package com.replica.mtn.app

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.replica.mtn.app.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    interface BottomSheetListener {
        fun onBottomSheetDismissed()
    }
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("Title") ?: "N/A"
        val customer = arguments?.getString("CUSTOMER") ?: "N/A"
        val amount = arguments?.getString("AMOUNT") ?: "0.00 ZAR"
        binding.tvCustmor.text = customer
        binding.tvAmount.text = amount
        binding.tvTitle.text = title
        binding.btnPrint.setOnClickListener {
            listener?.onBottomSheetDismissed()
            dismiss() }

        binding.btnNext.setOnClickListener {
            listener?.onBottomSheetDismissed()
            dismiss() }

    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener?.onBottomSheetDismissed()
    }

    companion object {
        var listener: BottomSheetListener? = null

        fun newInstance(title: String,customer: String, amount: String,list: BottomSheetListener): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putString("Title", title)
            args.putString("CUSTOMER", customer)
            args.putString("AMOUNT", amount)
            listener=list
            fragment.arguments = args
            return fragment
        }
    }


}
