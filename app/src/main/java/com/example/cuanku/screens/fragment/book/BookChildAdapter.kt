package com.example.cuanku.screens.fragment.book

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemBookChildBinding
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.DataDailyItem

class BookChildAdapter: RecyclerView.Adapter<BookChildAdapter.ViewHolder>() {

    var listItem = ArrayList<DataDailyItem>()

    fun setData(listData: ArrayList<DataDailyItem>) {
//        listItem.add(listData)
        this.listItem = listData
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemBookChildBinding.bind(view)

        fun bindData(item: DataDailyItem) {
            binding.run {
                txtKategori.text = item.savings?.name
                txtDetail.text = item.date

                val type = item.type
                if (type == "out") {
                    txtNominal.text = "-${convertToRupiah(item.nominal?.toDouble())}"
                    txtNominal.setTextColor(Color.parseColor("#ED2626"))
                } else {
                    txtNominal.text = "+${convertToRupiah(item.nominal?.toDouble())}"
                    txtNominal.setTextColor(Color.parseColor("#4CAF50"))
                }

                val context: Context = imgIcon.context
                val image = item.savings?.image
                val packageName = context.applicationContext.packageName
                val imgId: Int =
                    context.resources.getIdentifier("$packageName:drawable/$image", null, null)
                imgIcon.setImageBitmap(BitmapFactory.decodeResource(context.resources, imgId))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book_child, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


}