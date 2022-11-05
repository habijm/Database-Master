package com.chirikualii.materidb.ui.home.popularMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirikualii.materidb.data.model.Movie
import com.chirikualii.materidb.data.repository.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMovieViewModel(private val repo: MovieRepo) :ViewModel() {

    private val _listMovie = MutableLiveData<List<Movie>>()
    val listMovie : LiveData<List<Movie>> =_listMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun doGetPopularMovie(){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO){
            val listData = repo.getPopularMovie()

            withContext(Dispatchers.Main){
                _listMovie.value = listData
                _isLoading.value = false
            }
        }
    }
}