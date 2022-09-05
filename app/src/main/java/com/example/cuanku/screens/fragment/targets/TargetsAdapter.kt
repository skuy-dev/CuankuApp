package com.example.cuanku.screens.fragment.targets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemTargetsBinding
import com.example.cuanku.helper.formatRupiah
import com.example.cuanku.response.DataListTargets
import java.text.NumberFormat
import java.util.*

class TargetsAdapter : RecyclerView.Adapter<TargetsAdapter.ViewHolder>() {

//    private val differCallback = object : DiffUtil.ItemCallback<DataListTargets>() {
//
//        override fun areItemsTheSame(oldItem: DataListTargets, newItem: DataListTargets): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(
//            oldItem: DataListTargets,
//            newItem: DataListTargets
//        ): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this, differCallback)

    private var mainList = ArrayList<DataListTargets>()


    fun setData(mainList: ArrayList<DataListTargets>) {
//        val diffCallback = AdapterDiffCallback(this.mainList, mainList)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.mainList.clear()
//        this.mainList.addAll(mainList)
        this.mainList = mainList
        notifyDataSetChanged()
//        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_targets, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mainList[position])
//        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
//        return differ.currentList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTargetsBinding.bind(view)

        fun bindData(item: DataListTargets) {
            binding.run {
                txtNamaTarget.text = item.name
                txtDuration.text = "Target Tercapai ${item.duration}"
                txtNominal.text = formatRupiah(item.nominal?.toDouble())
            }
        }

    }


}