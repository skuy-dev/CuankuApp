package com.example.cuanku.screens.fragment.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemBookParentBinding
import com.example.cuanku.response.DataDailyItem
import com.example.cuanku.response.DataItemCombined

class BookParentAdapter: RecyclerView.Adapter<BookParentAdapter.ViewHolder>() {

    var listData = ArrayList<DataItemCombined>()

    fun setData(listData: Map<String, ArrayList<DataDailyItem>>) {
        this.listData.clear()
        for ((key, value) in  listData) {

            this.listData.add(DataItemCombined(key, value))
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemBookParentBinding.bind(view)

        fun bindChildData(item: DataItemCombined) {
            val bookChildAdapter = BookChildAdapter()
            binding.rvChild.apply {
                adapter = bookChildAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            bookChildAdapter.setData(item.data)
        }

       fun bindParentData(item: DataItemCombined) {
           binding.apply {
               txtDate.text = item.date
           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book_parent, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindParentData(listData[position])
        holder.bindChildData(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}