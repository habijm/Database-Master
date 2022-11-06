package com.chirikualii.materidb.ui.detailMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.chirikualii.materidb.R
import com.chirikualii.materidb.data.local.MovieDbImpl
import com.chirikualii.materidb.data.local.entity.MovieEntity
import com.chirikualii.materidb.data.model.Movie
import com.chirikualii.materidb.databinding.ActivityDetailMovieBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    var isFavorite = false

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieDb: MovieDbImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init Db
        movieDb = MovieDbImpl(this)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if (movie?.bookmark == 0) {
            isFavorite = false
            binding.fabFav.setImageResource(R.drawable.ic_bookmark_outline)
        } else {
            isFavorite = true
            binding.fabFav.setImageResource(R.drawable.ic_bookmark)

        }


        binding.txtTitleMovie.text = movie?.title
        binding.txtReleaseDate.text = movie?.releaseDate
        binding.txtOverview.text = movie?.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie?.imagePoster}")
            .into(binding.imgPoster)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie?.backdrop}")
            .into(binding.imgBackdrop)

        binding.fabFav.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                binding.fabFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_bookmark
                    )
                )
            } else {
                binding.fabFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_bookmark_outline
                    )
                )

            }
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    //Update Movie toDatabase
                    var bookmark = 0
                    if (isFavorite) {
                        bookmark = 1
                    } else {
                        bookmark = 0
                    }
                    val movieEntity = MovieEntity(
                        movieId = movie?.movieId.toString(),
                        title = movie?.title.toString(),
                        releaseDate = movie?.releaseDate.toString(),
                        imagePoster = movie?.imagePoster.toString(),
                        backdrop = movie?.backdrop.toString(),
                        overview = movie?.overview.toString(),
                        typeMovie ="",
                        bookmark = bookmark
                    )
                    movieDb.getDatabase().movieDao().updateMovieWithQuery(
                        movieId = movie?.movieId.toString(),
                        bookmark = bookmark
                    )
                }
            }
        }
    }
}