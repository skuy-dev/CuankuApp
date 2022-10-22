package com.example.cuanku.screens.activity.dashboard

import android.view.LayoutInflater
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.databinding.ActivityDashboardBinding
import com.example.cuanku.screens.fragment.book.BookFragment
import com.example.cuanku.screens.fragment.targets.TargetsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private val defaultFragment = BookFragment()

    override fun setLayout(inflater: LayoutInflater): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(inflater)
    }

    override fun initialization() {
        setupBotNav()
        openMainFragment()
    }

    override fun observeViewModel() {
    }


    private fun setupBotNav() {
        binding.botNav.setItemSelected(R.id.menuBuku)
        binding.botNav.setOnItemSelectedListener { nav ->
            when(nav) {
                R.id.menuBuku -> {
                    openMainFragment()
                }
                R.id.menuTarget -> {
                    val targetFragment = TargetsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, targetFragment).commit()
                }
            }
        }
    }

    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContainer, defaultFragment)
        transaction.commit()
    }

}

