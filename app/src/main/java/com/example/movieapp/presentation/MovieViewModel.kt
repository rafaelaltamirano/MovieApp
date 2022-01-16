package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp.core.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

//la instancia del VM debe ser vacia por eso creo la factory para crear la instancia
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
        //le digo a la vista que la informacion se esta cargando
        emit(Resource.Loading())
//ejecuto la corrutina que nos trae la info del servidor
        try {
            emit(Resource.Success(Triple(repo.getUpComingMovies(),repo.getTopRateMovie(),repo.getPopularMovies())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

// nos da la posibilidad de crear nuestras propias clases con este tipo de parametros en el constructor
class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //le digo al vm como se crea una instancia del repositorio
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

//como llamar al metodo live data desde nuestra vista y como obtener a informacion de las proxximas peliculas haciendo esa llamada