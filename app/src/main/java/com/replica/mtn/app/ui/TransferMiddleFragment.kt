package com.replica.mtn.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ncorti.slidetoact.SlideToActView
import com.replica.mtn.app.BottomSheetFragment
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.FragmentGalleryBinding
import com.replica.mtn.app.databinding.SettingFragmentBinding
import com.replica.mtn.app.databinding.TopUpFragmentBinding
import com.replica.mtn.app.databinding.TransferFragmentBinding

class TransferMiddleFragment : Fragment(),BottomSheetFragment.BottomSheetListener {

    private var _binding: TransferFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TransferFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.exampleGrayOnGreen.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                if(binding.tvGetCountry.text.isNotEmpty() && binding.tvTopUpAmt.text.isNotEmpty())
                {
                    val bottomsheet = BottomSheetFragment.newInstance("TRANSFER SUCCESSFUL",binding.tvGetCountry.text.toString(),binding.tvTopUpAmt.text.toString()+" ZAR",this@TransferMiddleFragment)
                    bottomsheet.show(activity!!.supportFragmentManager, "TranferFragment")
                    binding.tvGetCountry.setText("")
                    binding.tvTopUpAmt.setText("")
                    binding.mainLayout.isVisible = false
                }
                else {
                    Toast.makeText(requireContext(), "Please Filled All Fields", Toast.LENGTH_LONG)
                        .show()
                }
                binding.exampleGrayOnGreen.setCompleted(false,true)
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
}