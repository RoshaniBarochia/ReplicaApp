package com.replica.mtn.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.replica.mtn.app.OnItemClickListener
import com.replica.mtn.app.R

class PurchaseAdapter(
    private val countries: ArrayList<String>,
    private var itemClickListener: OnItemClickListener,
    val context: Context
) : RecyclerView.Adapter<PurchaseAdapter.CountryViewHolder>() {
    var pPos = -1
    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryTextView: TextView = itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryTextView.text = countries[position]
        holder.countryTextView.background = ContextCompat.getDrawable(context,R.drawable.selector_button_secondary_half)

        holder.itemView.setOnClickListener {
            if(pPos > -1)
                notifyItemChanged(pPos)
            holder.countryTextView.background = ContextCompat.getDrawable(context,R.drawable.selector_button_secondary_half_yellow)
            itemClickListener.onItemClick(position)
            pPos=position
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}