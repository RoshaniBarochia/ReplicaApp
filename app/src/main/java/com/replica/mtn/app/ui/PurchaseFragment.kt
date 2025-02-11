package com.replica.mtn.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ncorti.slidetoact.SlideToActView
import com.replica.mtn.app.BottomSheetFragment
import com.replica.mtn.app.OnItemClickListener
import com.replica.mtn.app.R
import com.replica.mtn.app.adapter.PurchaseAdapter
import com.replica.mtn.app.databinding.PurchaseFragmentBinding

class PurchaseFragment : Fragment(), BottomSheetFragment.BottomSheetListener, OnItemClickListener {
    private val countries = ArrayList<String>(4)
    private var _binding: PurchaseFragmentBinding? = null
    private val binding get() = _binding!!
    var topUpAmt = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PurchaseFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topUpAmt.text = resources.getString(R.string.voucher_amount_s, "")

        countries.add("10")
        countries.add("100")
        countries.add("200")
        countries.add("500")
        binding.recyclerView.adapter = PurchaseAdapter(countries, this,requireContext())

        binding.exampleGrayOnGreen.onSlideCompleteListener =
            object : SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    if (binding.tvGetCountry.text.isNotEmpty() && topUpAmt.isNotEmpty()) {
                        val bottomsheet = BottomSheetFragment.newInstance(
                            "VOUCHER PURCHASE SUCCESSFUL",
                            binding.tvGetCountry.text.toString(),
                            topUpAmt + " ZAR",
                            this@PurchaseFragment
                        )
                        bottomsheet.show(activity!!.supportFragmentManager, "TranferFragment")
                        binding.tvGetCountry.setText("")
                        binding.topUpAmt.text = resources.getString(R.string.voucher_amount_s, "")
                        binding.mainLayout.isVisible = false


                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Please Filled All Fields",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    binding.exampleGrayOnGreen.setCompleted(false, true)
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBottomSheetDismissed() {
        binding.mainLayout.isVisible = true
    }

    override fun onItemClick(pos: Int) {
        topUpAmt = countries[pos]
        binding.topUpAmt.text = resources.getString(R.string.voucher_amount_s, topUpAmt)


    }
}