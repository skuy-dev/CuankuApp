package com.example.cuanku.screens.activity.book.kategori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityKategoriBookBinding
import com.example.cuanku.response.ListKategoriItem
import com.example.cuanku.screens.activity.book.BookViewModel
import com.example.cuanku.screens.activity.book.add.AddBookDailyDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KategoriBookActivity : BaseActivity<ActivityKategoriBookBinding>(),
    KategoriBookDialog.OnItemClickListener, KategoriBookAdapter.onItemClickListener,
    AddBookDailyDialog.onItemClickListener {

    private val viewModel: BookViewModel by viewModels()
    private lateinit var kategoriBookAdapter: KategoriBookAdapter

    override fun setLayout(inflater: LayoutInflater): ActivityKategoriBookBinding {
        return ActivityKategoriBookBinding.inflate(inflater)
    }

    override fun initialization() {
        setupKategori()
        setOnClickListener()

    }

    override fun observeViewModel() {
       getData()
    }

    private fun getData() {
        viewModel.getListKategori()
        viewModel.getListKategori.observe(this) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        dismissLoading()
                        kategoriBookAdapter.differ.submitList(data)
                    }
                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                is NetworkResult.Error -> {
                    dismissLoading()
                    response.message?.getContentIfNotHandled()?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupKategori() {
        kategoriBookAdapter = KategoriBookAdapter(this@KategoriBookActivity)
        binding.rvKategori.apply {
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
            adapter = kategoriBookAdapter
        }
    }

    private fun setOnClickListener() {
        binding.btnAddKategori.setOnClickListener {
            val dialog = KategoriBookDialog(this)
            dialog.show(supportFragmentManager,"KATEGORIBOOKDIALOG")
        }
    }

    override fun onItemClicked() {
        getData()
    }

    override fun onItemKategoriClicked(data : ListKategoriItem) {
        val dialog = AddBookDailyDialog(data.id.toString(), this)
        dialog.show(supportFragmentManager,"ADDDAILY")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(2)
    }

}