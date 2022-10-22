package com.example.cuanku.screens.activity.targets.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemHistoryTargetBinding
import com.example.cuanku.helper.convertDate
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.UserTargetsItem

class DetailTargetAdapter : RecyclerView.Adapter<DetailTargetAdapter.ViewHolder>() {

//    private val differCallback = object : DiffUtil.ItemCallback<UserTargetsItem>() {
//        override fun areItemsTheSame(oldItem: UserTargetsItem, newItem: UserTargetsItem): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(
//            oldItem: UserTargetsItem,
//            newItem: UserTargetsItem
//        ): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this, differCallback)

    var list = ArrayList<UserTargetsItem>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<UserTargetsItem>) {
        this.list = list
        notifyDataSetChanged()
//        notifyItemChanged(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history_target, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindData(differ.currentList[position])
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
//        return differ.currentList.size
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHistoryTargetBinding.bind(view)

        fun bindData(data: UserTargetsItem?) {
            binding.run {
                txtTanggal.text = convertDate(data?.created_at)
                txtNominal.text = convertToRupiah(data?.nominal?.toDouble())
            }
        }

    }
}