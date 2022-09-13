package com.example.cuanku.screens.fragment.targets

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cuanku.screens.fragment.targets.done.DoneTargetFragment
import com.example.cuanku.screens.fragment.targets.list.ListTargetFragment

class TargetVpAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = ListTargetFragment()
            1 -> fragment = DoneTargetFragment()
        }
        return fragment
    }

}