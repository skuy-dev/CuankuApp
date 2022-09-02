package com.example.cuanku.screens.activity.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.databinding.ActivityDashboardBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.screens.fragment.BookFragment
import com.example.cuanku.screens.fragment.WalletFragment
import com.example.cuanku.screens.fragment.targets.TargetsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
    }

    override fun observeViewModel() {
//        TODO("Not yet implemented")
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
                    R.id.menuBook -> {
                        callFragment(0, bookFragment)
                    }
                    R.id.menuWallet -> {
                        callFragment(1, targetsFragment)
                    }
                }
                false
            }
        }
    }

    private fun callFragment(position: Int, fragment: Fragment) {
        menuItem = menu.getItem(position)
        menuItem.isChecked = true
        fm.beginTransaction().hide(defaultFragment).show(fragment).commit()
        defaultFragment = fragment
    }


}