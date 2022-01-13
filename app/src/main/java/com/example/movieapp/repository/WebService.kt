package com.example.movieapp.repository

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// encargado de usar retrofit para traer la informacion del servidor  con los metodos get
interface WebService {
    @GET("movie/upcoming?")
    //la query debe ser la misma que en la url, la query se denota con ? y luego el nombre en este caso api_key
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated?")
    suspend fun getTopRatedMovie(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList
}

//genero la peticion al servidor

object RetrofitClient{

    //inicializa la variable cuando llamo al webservice, solo cuando lo llamo
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            //convierte la informacion que esta en json al tipo de modelo con la libreria GSON de google
            .addConverterFactory((GsonConverterFactory.create(GsonBuilder().create())))
            .build().create(WebService::class.java)
    }
}