package com.example.movieapp.core

import java.lang.Exception

// las clases selladas o sealed class sirven para proporcionar estados
//estos estados nos sirven para darselos a la informacion que traemos
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    //tenemos un estado de exito que  va a tener un valor que puede ser
    // cualquier tipo de dato pero me va a retornar un Resource de Success
    data class Success<out T>(val data: T): Resource<T>()
    //un error siempre retorna una excepcion por lo tanto no retornamos un generico
    data class Failure(val exception: Exception): Resource<Nothing>()
}