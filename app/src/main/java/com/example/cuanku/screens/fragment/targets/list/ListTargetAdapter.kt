package com.example.cuanku.screens.fragment.targets.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
//import coil.load
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemTargetsBinding
import com.example.cuanku.helper.Constants.PATH_IMAGE
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.ListTargetItem


class ListTargetAdapter(
    private val listener: OnItemClickListener,
    private val listenerDelete: OnDeleteListener
) : RecyclerView.Adapter<ListTargetAdapter.ViewHolder>() {

    var listData = ArrayList<ListTargetItem>()

    fun setData(listData : ArrayList<ListTargetItem>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_targets, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTargetsBinding.bind(view)

        fun bindData(data: ListTargetItem) {
            binding.run {
                txtNamaTarget.text = data.name
                txtDuration.text = "Target Tercapai ${data.duration}"
                txtNominal.text = convertToRupiah(data.nominal?.toDouble())
                txtCountdown.text = "${data.count_day} Hari Lagi !"
                imgTarget.load(PATH_IMAGE + data.image_url)
                val kakumpul = data.remaining?.let { data.nominal?.minus(it) }
                txtTerkumpul.text = convertToRupiah(kakumpul?.toDouble())
                val percentage = (kakumpul!!.toDouble() / data.nominal!! * 100).toInt()
                txtPersentase.text = "$percentage%"
                progressBar.progress = percentage

                itemView.setOnClickListener {
                    listener.onItemClicked(data)
                }

                btnDelete.setOnClickListener {
                    listenerDelete.onDeleteClicked(data)
                }
            }
        }

    }

    interface OnDeleteListener {
        fun onDeleteClicked(data: ListTargetItem)
    }

    interface OnItemClickListener {
        fun onItemClicked(data: ListTargetItem)
    }


}