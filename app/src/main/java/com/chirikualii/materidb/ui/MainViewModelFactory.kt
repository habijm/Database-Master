package com.chirikualii.materidb.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chirikualii.materidb.data.local.MovieDbImpl
import com.chirikualii.materidb.data.remote.ApiClient
import com.chirikualii.materidb.data.repository.MovieRepoImpl

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = MainViewModel(
            MovieRepoImpl(
                ApiClient.service,
                MovieDbImpl(context = context)
            )
        ) as T

        return viewModel
    }
}