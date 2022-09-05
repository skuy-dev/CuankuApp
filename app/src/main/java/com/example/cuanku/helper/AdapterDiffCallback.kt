package com.example.cuanku.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.cuanku.response.DataListTargets

class AdapterDiffCallback(
    private val mOldList: List<DataListTargets>,
    private val mNewList: List<DataListTargets>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldList.size

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].id == mNewList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].name == mNewList[newItemPosition].name
    }

}