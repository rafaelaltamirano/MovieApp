package com.example.movieapp.data.remote

import com.example.movieapp.core.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService

//El data source se encarga de buscar la informacion del servidor y traerla
//contiene los metodos finales para traer la info
//en los tres metodos se traen listas de peliculas ya que todos contienen los mismos valores
//dentro de data source creo la consulta al servidor con la val webService
class MovieDataSource(private val webService: WebService) {
    //debe ser suspend ya que la llamada al servidor tambien es suspend
    suspend fun getUpcomingMovies(): MovieList = webService.getUpComingMovies(AppConstants.API_KEY)
    //Puedo instanciar el constructor vacio porque se inicializo con un valor por defecto en el model
    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovie(AppConstants.API_KEY)
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}