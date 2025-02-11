package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.replica.mtn.app.OnItemClickListener
import com.replica.mtn.app.R
import com.replica.mtn.app.adapter.HierarchyModel
import com.replica.mtn.app.adapter.MyHierarchyAdapter
import com.replica.mtn.app.databinding.MyHierarchySecondFragmentBinding
import com.replica.mtn.app.utils.Loader

class MyHierarchySecondFragment : Fragment(), OnItemClickListener {
    val list = ArrayList<HierarchyModel>(2)
    private var _binding: MyHierarchySecondFragmentBinding? = null
    private val binding get() = _binding!!
    private var loader: Loader? = null
    private var name: String? = ""
    private var msi: String? = ""
    private var balance: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: MyHierarchySecondFragmentArgs by navArgs()
        name = args.name
        msi = args.msi
        balance = args.balance
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyHierarchySecondFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectCnt.text = name
        binding.newPin.text = getString(R.string.msisdn1) + msi
        binding.balance.text = "User Balance: $balance"

        val msisdn = SpannableString(binding.newPin.text.toString())
        msisdn.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.newPin.text = msisdn

        val bal = SpannableString(binding.balance.text.toString())
        bal.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            12,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.balance.text = bal

        val orderId = SpannableString(binding.tvHierarchy.text.toString())
        orderId.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            18,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvHierarchy.text = orderId

        val orderId1 = SpannableString(binding.tvReseller.text.toString())
        orderId1.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            15,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvReseller.text = orderId1

        loader = Loader(requireContext())
        list.add(HierarchyModel("Dealer 1-1-1", "27640101010", "100,000.00 ZAR"))
        list.add(HierarchyModel("Dealer 1-1-2", "27640101020", "100,000.00 ZAR"))
        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }

        loader?.start()
        Handler(Looper.getMainLooper()).postDelayed({
            loader?.stop()
            binding.recyclerView.adapter = MyHierarchyAdapter(list, requireContext(), this)
        }, 1500)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(pos: Int) {

    }
}