package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient
import com.example.movieapp.ui.movie.adapters.MovieAdapter
import com.example.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.UpcompingConcatAdapter

//le aplico el metodo onlick de MovieAdapter para poder clickear sobre una pelicula
class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding

    //by es un delegador
    //le cedo mi trabajo a algo mas para que haga el trabajo por mi
    //le cedo a alguien mas para que cree la instancia de viewModel
    private val viewModel by viewModels<MovieViewModel> {
        //paso la dependencia cuando creo la isntancia del vm
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))
    }

    //setup de concatadapter
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        //instancia vacia de concatAdapter
        concatAdapter = ConcatAdapter()


        //view lifeciclye owner maneja los ciclos de vida del observe, para no tener varias emisiones del live data
        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    //primero aparece el loading
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // una vez que tenemos la pelicula se oculta
                    binding.progressBar.visibility = View.GONE
                    //configuro concat adapter para mostrar las peliculas (modulos) en el orden que yo quiera
                    concatAdapter.apply {
                        //concatenaAdaptadores
//                        result.data.first = movieList de upcomping y results es el resultado de esa lista
                        // el orden de los numeros es igual al orden en que se muestran las secciones
                        addAdapter(
                            0,
                            UpcompingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )

                    }
                    //binding del rv principal el cual contiene a su vez cada uno de los rv de cada una de los tipos de peliculas
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

    //cuando clickeo alguno de los elementos de la seccion se ejecuta este call back
    override fun onMovieClick(movie: Movie) {
        //uso safe Args
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date,
        )
        findNavController().navigate(action)
    }

}