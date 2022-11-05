package com.chirikualii.materidb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.chirikualii.materidb.R
import com.chirikualii.materidb.databinding.ActivityMainBinding
import com.chirikualii.materidb.ui.adapter.HomePagerAdapter
import com.chirikualii.materidb.ui.adapter.MovieListAdapter
import com.chirikualii.materidb.ui.favorite.FavoriteFragment
import com.chirikualii.materidb.ui.home.HomeFragment
import com.chirikualii.materidb.ui.home.nowPlaying.NowPlayingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterPagerAdapter: HomePagerAdapter

    private val listFragment = listOf(HomeFragment(), FavoriteFragment())
    private val listTitle = listOf("Home", "Favorite")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup adapter
        adapterPagerAdapter = HomePagerAdapter(
            this,
            listFragment = listFragment,
            listTitle = listTitle
        )

        binding.viewPager.adapter = adapterPagerAdapter
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeMenu -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.favoriteMenu -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }

    }

    private fun observeView() {

    }
}

