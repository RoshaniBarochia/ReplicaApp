package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.replica.mtn.app.FirstActivity
import com.replica.mtn.app.MainActivity
import com.replica.mtn.app.OnItemClickListener
import com.replica.mtn.app.R
import com.replica.mtn.app.adapter.HierarchyModel
import com.replica.mtn.app.adapter.MyHierarchyAdapter
import com.replica.mtn.app.databinding.IncentiveHistoryFragmentBinding
import com.replica.mtn.app.databinding.IncentiveHistorySecondFragmentBinding
import com.replica.mtn.app.databinding.MyHierarchyFragmentBinding
import com.replica.mtn.app.databinding.TransactionHistoryFragmentBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.get
import com.replica.mtn.app.pref.PreferenceHelper.set
import com.replica.mtn.app.utils.Loader
import java.text.SimpleDateFormat
import java.util.Locale

class MyHierarchyFragment : Fragment(),OnItemClickListener {
    val list = ArrayList<HierarchyModel>(2)
    private var _binding: MyHierarchyFragmentBinding? = null
    private val binding get() = _binding!!
    private var loader: Loader? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyHierarchyFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = Loader(requireContext())
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
        list.clear()
        list.add(HierarchyModel("Sub Distributor 1-1","27640101000", "9,811,500.00 ZAR"))
        list.add(HierarchyModel("Sub Distributor 1-2","27640102000", "9,800,500.00 ZAR"))
        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }

        loader?.start()
        Handler(Looper.getMainLooper()).postDelayed({
            loader?.stop()
            binding.recyclerView.adapter=MyHierarchyAdapter(list,requireContext(),this)
        },1500)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(pos: Int) {
        findNavController().navigate(
            MyHierarchyFragmentDirections.actionMyHierarchyToMyHierarchySecond(
                list[pos].name,list[pos].zar,list[pos].balance
            )
        )
    }
}