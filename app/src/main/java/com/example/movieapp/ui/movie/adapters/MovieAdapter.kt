package com.example.movieapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.BaseViewHolder
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.MovieItemBinding

//adaptador principal que ayudara a inflar cada uno de los elementos
// pido un viewgolder base que sera usado en todos los adapters
//* = cualquier tipo de viewholder
//inicializo el adapter con la lista de peliculas y la interface que cree mas abajo
class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //info cada elemento de la vista
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = MoviesViewHolder(itemBinding, parent.context)
        //attacheo el click de cada pelicula para  navegar a detalle
        itemBinding.root.setOnClickListener {
            // obtengo la posicion de la lista que estoy clickeando y la guarda en una posicion
            //no pisition: pisicion -1
            //caso contrario devuelve setOnClickListener que es igual a no obtener ninguna posicion
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    //a cada elemento de la lista le coloco el elemento
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
      when(holder){
          is MoviesViewHolder -> holder.bind(movieList[position])
      }
    }

    override fun getItemCount(): Int = movieList.size

    //creo un view holder
    //con inner es hijo de la clase MoviewAdapter y forma parte de el si muere uno
    // muere el otro para no dejar fugas de memoria
    //binding.root hace referencia a toda la layout completa para acceder a todos los metodos de esa layout
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            //cargo la imagen en este caso poster, dentro del image view
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop().into(binding.imgMovie)
        }
    }
}