package com.chirikualii.materidb.ui.detailMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.chirikualii.materidb.R
import com.chirikualii.materidb.data.model.Movie
import com.chirikualii.materidb.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding :ActivityDetailMovieBinding

    var isFavorite = false
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)


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
            if(isFavorite){
                binding.fabFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark))
            }else{
                binding.fabFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_outline))

            }
        }
    }
}