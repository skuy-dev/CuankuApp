package com.example.cuanku.screens.activity.targets.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemHistoryTargetBinding
import com.example.cuanku.response.DataItem
import com.example.cuanku.response.UserTargetsItem

class DetailTargetAdapter : RecyclerView.Adapter<DetailTargetAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<UserTargetsItem>() {
        override fun areItemsTheSame(oldItem: UserTargetsItem, newItem: UserTargetsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserTargetsItem,
            newItem: UserTargetsItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history_target, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHistoryTargetBinding.bind(view)

        fun bindData(data: UserTargetsItem?) {
            binding.run {
                txtTanggal.text = data?.createdAt
                txtNominal.text = data?.nominal.toString()
            }
        }

    }
}