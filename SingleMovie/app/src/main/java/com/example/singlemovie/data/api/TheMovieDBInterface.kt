package com.example.singlemovie.data.api

import com.example.singlemovie.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDBInterface {

//    https://api.themoviedb.org/3/movie/top_rated?api_key=11459cff1c1ce00e3202addab99f3a91
//    https://api.themoviedb.org/3/movie/278?api_key=11459cff1c1ce00e3202addab99f3a91
//    https://api.themoviedb.org/3/
//    https://image.tmdb.org/t/p/w500/


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}