package com.chirikualii.materidb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chirikualii.materidb.R
import com.chirikualii.materidb.databinding.FragmentHomeBinding
import com.chirikualii.materidb.ui.adapter.HomePagerAdapter
import com.chirikualii.materidb.ui.favorite.FavoriteFragment
import com.chirikualii.materidb.ui.home.nowPlaying.NowPlayingFragment
import com.chirikualii.materidb.ui.home.popularMovie.PopularMovieFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val listFragment = listOf(NowPlayingFragment(), PopularMovieFragment())
    private val listTitle = listOf("Now Playing", "Popular Movie")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewPagerAdapter = HomePagerAdapter(
            requireActivity(),
            listFragment = listFragment,
            listTitle = listTitle
        )

        binding.viewPager.adapter = homeViewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Now Playing"
                1 -> tab.text = "Popular Movie"
            }
        }.attach()



    }

}