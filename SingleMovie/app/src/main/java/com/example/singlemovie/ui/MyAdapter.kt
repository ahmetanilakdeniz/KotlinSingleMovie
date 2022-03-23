package com.example.singlemovie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.singlemovie.R
import com.example.singlemovie.data.vo.MovieDetails


class MyAdapter(private val userList: ArrayList<MovieDetails>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {


        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener) {

        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_single_movie, parent, false)
        return MyViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class MyViewHolder(private val view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {

        fun bind(it: MovieDetails) {

            val  movie_title = view.findViewById<TextView>(R.id.movieTitle)

            movie_title.text = it.originalTitle
            Glide.with(view.context).load(it.posterPath).into(itemView as ImageView)

        }

        init {

            itemView.setOnClickListener{
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }
}






