package com.replica.mtn.app.utils

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.replica.mtn.app.R

class CustomMarkerView(context: Context) : MarkerView(context, R.layout.marker_view_layout) {

    private val txtValue: TextView = findViewById(R.id.txtValue)
    private val txtTitle: TextView = findViewById(R.id.txtTitle)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if(e?.x?.toInt() == 2) {
            txtValue.text = "${e?.x?.toInt()}               | 20,000,000.00 ZAR | 0.00 ZAR"
            txtTitle.text =
                "${resources.getString(R.string.transaction1)}  | Amount                             | Earning"
        }else{
            txtValue.text = "${e?.x?.toInt()}              | 2,000.00 ZAR | 0.00 ZAR"
            txtTitle.text =
                "${resources.getString(R.string.transaction1)} | Amount                | Earning"
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-width / 2).toFloat(), -height.toFloat())
    }
}
