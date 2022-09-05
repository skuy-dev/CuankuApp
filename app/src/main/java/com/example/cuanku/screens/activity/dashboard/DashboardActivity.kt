package com.example.cuanku.screens.activity.dashboard

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.databinding.ActivityDashboardBinding
import com.example.cuanku.screens.fragment.BookFragment
import com.example.cuanku.screens.fragment.targets.TargetsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private val bookFragment: Fragment = BookFragment()
    private val targetsFragment: Fragment = TargetsFragment()

    private val fm: FragmentManager = supportFragmentManager

    var defaultFragment: Fragment = bookFragment
    lateinit var menu: Menu
    lateinit var menuItem: MenuItem

    override fun setLayout(inflater: LayoutInflater): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(inflater)
    }

    override fun initialization() {
        setupBotNav()
        setOnClickListener()
    }

    override fun observeViewModel() {
    }

    private fun setupBotNav() {
        fm.beginTransaction().add(R.id.flContainer, bookFragment).show(bookFragment).commit()
        fm.beginTransaction().add(R.id.flContainer, targetsFragment).hide(targetsFragment).commit()

        menu = binding.botNav.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true


        binding.botNav.apply {
            background = null
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menuBuku -> {
                        callFragment(0, bookFragment)
                    }
                    R.id.menuTarget -> {
                        callFragment(1, targetsFragment)
                    }
                }
                false
            }
        }
    }

    private fun setOnClickListener() {
        binding.fab.setOnClickListener {
            val dialog = DashboardDialogFragment()
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    private fun callFragment(position: Int, fragment: Fragment) {
        menuItem = menu.getItem(position)
        menuItem.isChecked = true
        fm.beginTransaction().hide(defaultFragment).show(fragment).commit()
        defaultFragment = fragment
    }


}