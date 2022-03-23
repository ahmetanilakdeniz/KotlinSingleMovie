package com.example.singlemovie.ui.single_movie_details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.singlemovie.R
import com.example.singlemovie.data.api.POSTER_BASE_URL
import com.example.singlemovie.data.api.TheMovieDBClient
import com.example.singlemovie.data.api.TheMovieDBInterface
import com.example.singlemovie.data.repository.NetworkState
import com.example.singlemovie.data.vo.MovieDetails


abstract class SingleMovie  : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    private lateinit var movieTitle: TextView
    private lateinit var movieRating: TextView
    private lateinit var ivMoviePoster: ImageView
    private lateinit var movieOverview: TextView
    private lateinit var moviePopularity: TextView
    private lateinit var progressBar : ProgressBar
    private lateinit var txtError : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

    val movieId: Int = intent.getIntExtra("id",1)

    val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this) {
            bind(it)
        }

        viewModel.networkState.observe(this) {
            progressBar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txtError.visibility = if (it == NetworkState.EROR) View.VISIBLE else View.GONE

        }
    }

     private fun bind(it: MovieDetails) {
        movieTitle.text = it.originalTitle
        movieRating.text = it.rating.toString()
        movieOverview.text = it.overview
        moviePopularity.text = it.popularity.toString()

        val moviePosterUrl : String = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterUrl)
            .into(ivMoviePoster)

    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        return ViewModelProvider(this,object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(movieRepository, movieId) as T
            }
        })[SingleMovieViewModel::class.java]
        }
    }

//        return ViewModelProvider(ViewModelStoreOwner(this,object : ViewModelProvider.Factory{
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                   @Suppress("UNCHECKED_CAST")
//                    return SingleMovieViewModel(movieRepository,movieId) as T
//            }
//
//    })
//                [SingleMovieViewModel::class.java]
//    }
