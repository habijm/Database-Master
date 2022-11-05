package com.chirikualii.materidb.ui.home.popularMovie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chirikualii.materidb.data.local.MovieDbImpl
import com.chirikualii.materidb.data.remote.ApiClient
import com.chirikualii.materidb.data.repository.MovieRepoImpl

class PopularMovieViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = PopularMovieViewModel(
            MovieRepoImpl(
                ApiClient.service,
                MovieDbImpl(context)
            )
        ) as T

        return viewModel
    }

}