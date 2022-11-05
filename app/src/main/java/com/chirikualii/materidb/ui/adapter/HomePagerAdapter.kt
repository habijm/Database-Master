package com.chirikualii.materidb.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStateManagerControl
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chirikualii.materidb.ui.home.nowPlaying.NowPlayingFragment
import com.chirikualii.materidb.ui.home.popularMovie.PopularMovieFragment

class HomePagerAdapter(fragmentActivity: FragmentActivity, val listFragment :List<Fragment>,val listTitle:List<String>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return listFragment.size

    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}