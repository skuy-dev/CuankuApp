package com.example.cuanku.screens.fragment.targets

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemTargetsBinding
import com.example.cuanku.response.DataListTargets

class TargetsAdapter :
    BaseQuickAdapter<DataListTargets, TargetsAdapter.ViewHolder>(R.layout.item_targets) {
    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemTargetsBinding.bind(view)

        fun bindData(item: DataListTargets) {
            binding.run {
                txtNamaTarget.text = item.name
            }
        }

    }

    override fun convert(holder: ViewHolder, item: DataListTargets) {
        holder.bindData(item)
    }
}