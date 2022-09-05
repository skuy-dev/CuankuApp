package com.example.cuanku.screens.fragment.targets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemTargetsBinding
import com.example.cuanku.helper.Constants.PATH_IMAGE
import com.example.cuanku.helper.formatRupiah
import com.example.cuanku.response.DataListTargets
import java.util.*

class TargetsAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TargetsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataListTargets>() {

        override fun areItemsTheSame(oldItem: DataListTargets, newItem: DataListTargets): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataListTargets,
            newItem: DataListTargets
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_targets, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTargetsBinding.bind(view)

        fun bindData(data: DataListTargets) {
            binding.run {
                txtNamaTarget.text = data.name
                txtDuration.text = "Target Tercapai ${data.duration}"
                txtNominal.text = formatRupiah(data.nominal?.toDouble())
                imgTarget.load(PATH_IMAGE + data.image_url)
                itemView.setOnClickListener {
                    listener.onItemClicked(data)
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(data: DataListTargets)
    }


}