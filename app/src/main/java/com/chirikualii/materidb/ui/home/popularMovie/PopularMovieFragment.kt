package com.chirikualii.materidb.ui.home.popularMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chirikualii.materidb.databinding.FragmentPopularMovieBinding
import com.chirikualii.materidb.ui.adapter.MovieListAdapter


class PopularMovieFragment : Fragment() {

    private lateinit var binding : FragmentPopularMovieBinding

    private lateinit var adapter: MovieListAdapter

    private val mViewModel: PopularMovieViewModel by viewModels(
        factoryProducer = { PopularMovieViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPopularMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setup adapter
        adapter = MovieListAdapter()
        binding.rvMovie.adapter = adapter

        mViewModel.doGetPopularMovie()
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