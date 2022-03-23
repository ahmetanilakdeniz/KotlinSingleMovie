package com.example.singlemovie.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.singlemovie.data.api.TheMovieDBInterface
import com.example.singlemovie.data.repository.MovieDetailsNetworkDataSource
import com.example.singlemovie.data.repository.NetworkState
import com.example.singlemovie.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

      lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

      fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

          movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
          movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

          return movieDetailsNetworkDataSource.downloadedMovieResponse
      }
      fun getMovieDetailsNetworkState(): LiveData<NetworkState>{
          return movieDetailsNetworkDataSource.networkState
      }
}