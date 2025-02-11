package com.replica.mtn.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.replica.mtn.app.OnItemClickListener
import com.replica.mtn.app.R
import com.replica.mtn.app.databinding.ItemMyHierarchyBinding
import com.replica.mtn.app.databinding.ItemTranHistoryBinding

class MyHierarchyAdapter(
    val list: ArrayList<HierarchyModel>,
    val context: Context,
    private var itemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<MyHierarchyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMyHierarchyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mBinding!!.tvTitle.text = list[position].name
        holder.mBinding.tvMsi.text = list[position].zar
        holder.mBinding.tvCurrent.text = list[position].balance
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(binding: ItemMyHierarchyBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding: ItemMyHierarchyBinding? = DataBindingUtil.bind(itemView)

    }
}