package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.remote.MovieDataSource

//la implementacion de donde buscar la informacion
//creo una instancia de data sorce para poder acceder a los metodos
//creo una instancia dentro del constructor llamada dataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {
    //= ES COMO EL RETURN, igualamos la funcion a su resultado
    override suspend fun getUpComingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRateMovie(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}