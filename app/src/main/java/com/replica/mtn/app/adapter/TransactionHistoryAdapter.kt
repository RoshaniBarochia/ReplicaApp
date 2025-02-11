package com.replica.mtn.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.ItemTranHistoryBinding

class TransactionHistoryAdapter(
    val context: Context
) : RecyclerView.Adapter<TransactionHistoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemTranHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(position == 5){
            holder.mBinding!!.view1.setBackgroundColor(context.resources.getColor(R.color.green,null))
            holder.mBinding.tvTitle.text = "1000.00 ZAR - OTHER"
            //holder.mBinding.imgArrow.setImageResource(R.drawable.ic_arrow_right)
        }else{
            holder.mBinding!!.view1.setBackgroundColor(context.resources.getColor(android.R.color.holo_red_dark,null))
            holder.mBinding.tvTitle.text = "40.00 ZAR - OTHER"
            //holder.mBinding.imgArrow.setImageResource(R.drawable.ic_arrow_down)

        }

    }


    override fun getItemCount(): Int {
        return 10
    }


    class MyViewHolder(binding: ItemTranHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding: ItemTranHistoryBinding? = DataBindingUtil.bind(itemView)

    }
}