package com.replica.mtn.app.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ncorti.slidetoact.SlideToActView
import com.replica.mtn.app.BottomSheetFragment
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.TopUpFragmentBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set


class TopUpFragment : Fragment(),BottomSheetFragment.BottomSheetListener  {

    private var _binding: TopUpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopUpFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!PreferenceHelper.preferences.getBoolean(PreferenceHelper.DIALOG,false)) {
            //show Dialog
            showCustomDialog()
            PreferenceHelper.preferences[PreferenceHelper.DIALOG] = true
        }

        binding.exampleGrayOnGreen.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                if(binding.tvGetCountry.text.isNotEmpty() && binding.tvTopUpAmt.text.isNotEmpty())
                {

                    val bottomsheet = BottomSheetFragment.newInstance("TOPUP SUCCESSFUL",binding.tvGetCountry.text.toString(),binding.tvTopUpAmt.text.toString()+" ZAR",this@TopUpFragment)
                    bottomsheet.show(activity!!.supportFragmentManager, "TopUpFragment")
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
    private fun showCustomDialog() {
        // Create the dialog
        val dialog = Dialog(requireContext())

        // Inflate the custom layout
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_dialog, null)

        // Set the custom layout to the dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)

        // Make the dialog's background transparent if necessary
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Set layout width and height for the dialog
        /*dialog.window?.setLayout(
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._400sdp), // Change as needed
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._300sdp)  // Change as needed
        )*/

        // Set click listener for the close button
        val closeButton: ImageView = view.findViewById(R.id.imgClose)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBottomSheetDismissed() {
        binding.mainLayout.isVisible = true
    }


}