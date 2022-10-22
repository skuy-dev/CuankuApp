package com.example.cuanku.screens.fragment.targets.done

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cuanku.R
import com.example.cuanku.databinding.ItemTargetsBinding
import com.example.cuanku.helper.Constants
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.ListTargetDoneItem

class DoneTargetAdapter : RecyclerView.Adapter<DoneTargetAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<ListTargetDoneItem>() {
        override fun areItemsTheSame(oldItem: ListTargetDoneItem, newItem: ListTargetDoneItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListTargetDoneItem, newItem: ListTargetDoneItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTargetsBinding.bind(view)

        fun bindData(data: ListTargetDoneItem) {
            binding.run {
                txtNamaTarget.text = data.name
                txtDuration.text = "Target Tercapai ${data.duration}"
                txtNominal.text = convertToRupiah(data.nominal?.toDouble())
//                txtCountdown.text = "${data.count_day} Hari Lagi !"
                imgTarget.load(Constants.PATH_IMAGE + data.image_url)
                val kakumpul = data.remaining?.let { data.nominal?.minus(it) }
                txtTerkumpul.text = convertToRupiah(kakumpul?.toDouble())
                val percentage = (kakumpul!!.toDouble() / data.nominal!! * 100).toInt()
                txtPersentase.text = "$percentage%"
                progressBar.progress = percentage

//                itemView.setOnClickListener {
//                    listener.onItemClicked(data)
//                }
            }
        }
    }

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


}