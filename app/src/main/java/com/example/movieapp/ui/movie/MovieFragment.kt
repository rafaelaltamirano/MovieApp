package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding

    //by es un delegador
    //le cedo mi trabajo a algo mas para que haga el trabajo por mi
    //le cedo a alguien mas para que cree la instancia de viewModel
    private val viewModel by viewModels<MovieViewModel> {
        //paso la dependencia cuando creo la isntancia del vm
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)


        //view lifeciclye owner maneja los ciclos de vida del observe, para no tener varias emisiones del live data
        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading..")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

}