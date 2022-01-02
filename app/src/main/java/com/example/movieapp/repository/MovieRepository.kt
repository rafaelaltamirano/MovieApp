package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

// la interfaz define los metodos que se van a usar en la implementacion, genera la firma del metodo
interface MovieRepository {
    //creo funciones de tipo suspendidas aplicando corrutinas
    suspend fun getUpComingMovies(): MovieList
    suspend fun getTopRateMovie(): MovieList
    suspend fun getPopularMovies(): MovieList
}