package com.chirikualii.materidb.ui.home.nowPlaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chirikualii.materidb.data.remote.ApiClient
import com.chirikualii.materidb.data.repository.MovieRepoImpl

class NowPlayingViewModelFactory :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = NowPlayingViewModel(
            MovieRepoImpl(ApiClient.service)
        ) as T

        return viewModel
    }

}