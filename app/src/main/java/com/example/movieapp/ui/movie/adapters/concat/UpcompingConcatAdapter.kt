package com.example.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.BaseContactHolder
import com.example.movieapp.databinding.UpcomingMoviesRowBinding
import com.example.movieapp.ui.movie.adapters.MovieAdapter

class UpcompingConcatAdapter (private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseContactHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseContactHolder<*> {
        val itemBinding =
            UpcomingMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseContactHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    //el tamaño total del adaptador solo va a ser uno en el caso del contact es de un solo, porque solo tiene un solo adapter(movies Adapter)
    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: UpcomingMoviesRowBinding) :
        BaseContactHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvUpcomingMovies.adapter = adapter
        }
    }
}