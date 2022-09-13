package com.example.cuanku.screens.fragment.targets

import android.view.LayoutInflater
import com.example.cuanku.R
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.databinding.FragmentTargetsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TargetsFragment : BaseFragment<FragmentTargetsBinding>() {

    private lateinit var vpAdapter: TargetVpAdapter


    override fun setLayout(inflater: LayoutInflater): FragmentTargetsBinding {
        return FragmentTargetsBinding.inflate(inflater)
    }

    override fun initialization() {
        setupViewPager()
    }

    override fun observeViewModel() {
    }

    private fun setupViewPager() {
        vpAdapter = TargetVpAdapter(parentFragmentManager, lifecycle)
        binding.run {
            this.viewPager.adapter = vpAdapter

            this.tabLayout.let {
                it.setSelectedTabIndicator(R.color.black)
                TabLayoutMediator(it, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "ListTarget"
                        1 -> tab.text = "Target Beres"
                    }
                }.attach()
            }
        }
    }

}