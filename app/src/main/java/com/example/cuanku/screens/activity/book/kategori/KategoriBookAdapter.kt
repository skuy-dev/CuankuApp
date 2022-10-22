package com.example.cuanku.screens.activity.book.kategori

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemKategoriBookBinding
import com.example.cuanku.response.KategoriBookItem
import com.example.cuanku.response.ListKategoriItem
import com.example.cuanku.response.ListTargetItem

class KategoriBookAdapter(
    private val listener: onItemClickListener
): RecyclerView.Adapter<KategoriBookAdapter.ViewHolder>() {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemKategoriBookBinding.bind(view)

        fun binData(data: ListKategoriItem) {
            binding.run {
                txtKategori.text = data.name
                val image = data.image

                val context: Context = imgIcon.context
                val packageName = context.applicationContext.packageName
                val imgId = context.resources.getIdentifier("$packageName:drawable/$image", null, null)
                imgIcon.setImageBitmap(BitmapFactory.decodeResource(context.resources, imgId))

                itemView.setOnClickListener {
                    listener.onItemKategoriClicked(data)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_kategori_book, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<ListKategoriItem>() {

        override fun areItemsTheSame(oldItem: ListKategoriItem, newItem: ListKategoriItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ListKategoriItem,
            newItem: ListKategoriItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    interface onItemClickListener {
        fun onItemKategoriClicked(data : ListKategoriItem)
    }
}