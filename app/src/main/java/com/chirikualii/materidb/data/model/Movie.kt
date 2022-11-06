package com.chirikualii.materidb.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val movieId:String,
    val title:String,
    val releaseDate:String,
    val imagePoster:String,
    val backdrop:String,
    val overview:String,
    val bookmark:Int = 0
) :Parcelable