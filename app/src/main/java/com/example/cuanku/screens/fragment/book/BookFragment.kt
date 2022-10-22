package com.example.cuanku.screens.fragment.book

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.R
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentBookBinding
import com.example.cuanku.screens.activity.book.BookViewModel
import com.example.cuanku.screens.activity.book.kategori.KategoriBookActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>() {

    private val viewModel: BookViewModel by viewModels()
    private lateinit var bookparentAdapter: BookParentAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentBookBinding {
        return FragmentBookBinding.inflate(inflater)
    }

    override fun initialization() {
        setupAdapter()
        setupView()
        setonClickListener()
    }

    override fun observeViewModel() {
        getData()
    }

    private fun getData() {
        viewModel.getDailyCatatan()
        viewModel.getDailyCatatan.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        this.dismissLoading()
                        bookparentAdapter.setData(data)
//                        bookAdapter.setData(data)
//                        bookAdapter.differ.submitList(data)
                    } else {
                        this.dismissLoading()
                    }
                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                else -> {}
            }
        }
    }

    private fun setupView() {
        binding.cardPemasukan.apply {
            txtType.text = "Pemasukan"
            txtType.setTextColor(Color.parseColor("#4CAF50"))
            indicator.setBackgroundResource(R.drawable.ic_up)
        }
        binding.cardPengeluaran.apply {
            txtType.text = "Pengeluaran"
            txtType.setTextColor(Color.parseColor("#ED2626"))
            indicator.setBackgroundResource(R.drawable.ic_down)
        }
    }

    private fun setupAdapter() {
        bookparentAdapter = BookParentAdapter()
        binding.rvDaily.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = bookparentAdapter

            isNestedScrollingEnabled = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            getData()
        }
    }

    private fun setonClickListener() {
        binding.btnAddKategori.setOnClickListener {
            val intent = Intent(context, KategoriBookActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivityForResult(intent, 2)
        }
    }

}