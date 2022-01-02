package com.example.movieapp.application


// OBJECT funciona como un sigleton,cada vez que usemos appConstants accedera desde un mismo objeto, es decir lo crea solo una vez
object AppConstants {

    //API
    const val BASE_URL="https://api.themoviedb.org/3/"
    const val API_KEY="73d92f173ff8ba5d9939ae435ce464ea"
}