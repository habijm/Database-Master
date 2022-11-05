package com.chirikualii.materidb.ui.home.nowPlaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.chirikualii.materidb.R
import com.chirikualii.materidb.databinding.ActivityMainBinding
import com.chirikualii.materidb.databinding.FragmentNowPlayingBinding
import com.chirikualii.materidb.ui.MainViewModel
import com.chirikualii.materidb.ui.MainViewModelFactory
import com.chirikualii.materidb.ui.adapter.MovieListAdapter


class NowPlayingFragment : Fragment() {

    private lateinit var binding : FragmentNowPlayingBinding

    private lateinit var adapter: MovieListAdapter

    private val mViewModel: NowPlayingViewModel by viewModels(
        factoryProducer = { NowPlayingViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNowPlayingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setup adapter
        adapter = MovieListAdapter()
        binding.rvMovie.adapter = adapter

        mViewModel.doGetNowPlayingMovie()
        observeView()
    }

    private fun observeView() {
        mViewModel.listMovie.observe(viewLifecycleOwner) {
            adapter.addItem(it)

        }

        mViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }


}