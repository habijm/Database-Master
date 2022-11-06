package com.chirikualii.materidb.data.repository

import android.util.Log
import com.chirikualii.materidb.data.local.MovieDbImpl
import com.chirikualii.materidb.data.local.entity.MovieEntity
import com.chirikualii.materidb.data.model.Movie
import com.chirikualii.materidb.data.model.MovieType
import com.chirikualii.materidb.data.remote.ApiService
import com.google.gson.Gson

class MovieRepoImpl(
    private val service: ApiService,
    private val movieDbImpl: MovieDbImpl
) : MovieRepo {

    override suspend fun getPopularMovie(): List<Movie> {
        try {
            val response = service.getPopularMovie()

            if (response.isSuccessful) {
                val listMovie = response.body()


                //Mapping From API
                val listMovieEntity = listMovie?.results?.map {
                    MovieEntity(
                        movieId = it.id.toString(),
                        title = it.title,
                        releaseDate = it.releaseDate,
                        imagePoster = it.posterPath,
                        overview = it.overview,
                        backdrop = it.backdropPath,
                        typeMovie = MovieType.popular
                    )
                }

                //Insert to db
                listMovieEntity?.forEach {
                    movieDbImpl.getDatabase().movieDao().insertMovie(it)
                }
                Log.d(
                    MovieRepoImpl::class.simpleName,
                    "getPopularMovie : ${Gson().toJsonTree(listMovieEntity)}"
                )
                return getPopularMovieLocal()
            } else {
                Log.e(
                    MovieRepoImpl::class.simpleName,
                    "getPopularMovie error code: ${response.code()}",
                )
                return getPopularMovieLocal()
            }
        } catch (e: Exception) {
            Log.e(MovieRepoImpl::class.simpleName, "getPopularMovie error :${e.message} ")
            return getPopularMovieLocal()
        }
    }

    override suspend fun getNowPlayingMovie(): List<Movie> {
        try {
            val response = service.getNowPlayingMovie()

            if (response.isSuccessful) {
                val listMovie = response.body()

                val listMovieEntity = listMovie?.results?.map {
                    MovieEntity(
                        movieId = it.id.toString(),
                        title = it.title,
                        releaseDate = it.releaseDate,
                        imagePoster = it.posterPath,
                        backdrop = it.backdropPath,
                        overview = it.overview,
                        typeMovie = MovieType.nowPlaying
                    )
                }

                listMovieEntity?.forEach {
                    movieDbImpl.getDatabase().movieDao().insertMovie(it)
                }


                Log.d(
                    MovieRepoImpl::class.simpleName,
                    "getNowPlayingMovie : ${Gson().toJsonTree(listMovie)}"
                )
                return getNowPlayingLocal()
            } else {
                Log.e(
                    MovieRepoImpl::class.simpleName,
                    "getNowPlayingMovie error code: ${response.code()}",
                )
                return getNowPlayingLocal()
            }
        } catch (e: Exception) {
            Log.e(MovieRepoImpl::class.simpleName, "getNowPlayingMovie error :${e.message} ")
            return getNowPlayingLocal()
        }
    }

    override suspend fun getPopularMovieLocal(): List<Movie> {
        var listData = listOf<Movie>()
        try {
            listData = movieDbImpl.getDatabase().movieDao().getListMoviePopular(
                MovieType.popular
            ).map {
                Movie(
                    title = it.title,
                    releaseDate = it.releaseDate,
                    imagePoster = it.imagePoster,
                    overview = it.overview,
                    backdrop = it.backdrop
                )
            }
            return listData

        } catch (e: Exception) {
            Log.e(MovieRepoImpl::class.simpleName, "getPopularMovieLocal: error ${e.message}")
            return listData

        }
    }

    override suspend fun getNowPlayingLocal(): List<Movie> {
        var listData = listOf<Movie>()
        try {
            listData = movieDbImpl.getDatabase().movieDao().getListMovieNowPlaying(
                MovieType.nowPlaying
            ).map {
                Movie(
                    title = it.title,
                    releaseDate = it.releaseDate,
                    imagePoster = it.imagePoster,
                    overview = it.overview,
                    backdrop = it.backdrop
                )
            }
            return listData
        } catch (e: Exception) {
            Log.e(MovieRepoImpl::class.simpleName, "getNowPlayingLocal: error ${e.message}")
            return listData
        }
    }
}