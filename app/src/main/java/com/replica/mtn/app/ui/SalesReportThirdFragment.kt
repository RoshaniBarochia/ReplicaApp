package com.replica.mtn.app.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.SalesReportThirdFragmentBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set
import com.replica.mtn.app.utils.CustomMarkerView


class SalesReportThirdFragment : Fragment() {

    private var _binding: SalesReportThirdFragmentBinding? = null
    private val binding get() = _binding!!
    private var validDateFrom: String? = ""
    private var validDateTo: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: SalesReportSecondFragmentArgs by navArgs()
        validDateFrom = args.fromDate
        validDateTo = args.toDate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SalesReportThirdFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectCnt.text =
            getString(R.string.sales_report_from_s_to_s, validDateFrom, validDateTo)

        binding.imgImage.setOnClickListener {
            findNavController().popBackStack()
        }
        if(!PreferenceHelper.preferences.getBoolean(PreferenceHelper.DIALOG_CHART,false)) {
            //show Dialog
            showCustomDialog()
            PreferenceHelper.preferences[PreferenceHelper.DIALOG_CHART] = true
        }
        //chart

        // Sample Data
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 0f)) // Jan 7
        entries.add(Entry(2f, 20000000.00f)) // Jan 7
        entries.add(Entry(3f, 0f))  // Jan 8
        entries.add(Entry(4f, 0f))  // Jan 9
        entries.add(Entry(5f, 0f)) // Jan 10



        // create a dataset and give it a type
        val set1 = LineDataSet(entries, "DataSet 1")

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1.setCubicIntensity(0.2f)
        set1.setDrawFilled(true)
        set1.setDrawCircles(false)
        set1.setColor(Color.BLACK)
        set1.setFillColor(Color.BLACK)
        set1.fillAlpha = 100
        set1.setDrawHorizontalHighlightIndicator(false)
        set1.fillFormatter = IFillFormatter { _, _ ->
            binding.lineChart.axisLeft.axisMinimum
        }


        // Gradient Fill
        val drawable = ContextCompat.getDrawable(requireContext(), com.replica.mtn.app.R.drawable.gradient_fill)
        set1.fillDrawable = drawable


        binding.lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        // Remove Grid and Axes
        binding.lineChart.axisLeft.isEnabled = false
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.isEnabled = false
        binding.lineChart.description.isEnabled = false
        binding.lineChart.legend.isEnabled = false


        // enable touch gestures
        binding.lineChart.setTouchEnabled(true)


        // enable scaling and dragging
        binding.lineChart.setDragEnabled(true)
        binding.lineChart.setScaleEnabled(true)


        // if disabled, scaling can be done on x- and y-axis separately
        binding.lineChart.setPinchZoom(true)

        binding.lineChart.setDrawGridBackground(false)
        binding.lineChart.maxHighlightDistance = 100f


        // Add Data to Chart
        val data = LineData(set1)
        data.setDrawValues(false)
        binding.lineChart.setData(data)

        // Custom Marker View on Touch
        val markerView = CustomMarkerView(requireContext())
        binding.lineChart.marker = markerView

        binding.lineChart.invalidate() // Refresh the chart


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



        // Set click listener for the close button
        val closeButton: ImageView = view.findViewById(R.id.imgClose)
        val imgImage: ImageView = view.findViewById(R.id.imgImage)
        imgImage.setImageResource(R.drawable.pinch_zoom1)
        Handler(Looper.getMainLooper()).postDelayed({
            imgImage.setImageResource(R.drawable.pinch_zoom2)
        },200)
        Handler(Looper.getMainLooper()).postDelayed({
            imgImage.setImageResource(R.drawable.pinch_zoom)
        },300)
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
}