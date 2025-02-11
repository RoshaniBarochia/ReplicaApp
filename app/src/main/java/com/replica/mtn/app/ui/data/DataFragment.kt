package com.replica.mtn.app.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.FragmentSlideshowBinding

class DataFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTrnHis.setOnClickListener {
            findNavController().navigate(R.id.transaction_history)
        }
        binding.tvSales.setOnClickListener {
            findNavController().navigate(R.id.sales_report)
        }
        binding.tvDipHis.setOnClickListener {
            findNavController().navigate(R.id.deposit_history)
        }
        binding.tvIntHis.setOnClickListener {
            findNavController().navigate(R.id.incentive_history)
        }
        binding.tvMyHir.setOnClickListener {
            findNavController().navigate(R.id.my_hierarchy)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}