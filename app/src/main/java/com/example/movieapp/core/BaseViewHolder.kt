package com.example.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//generico T: Se puede pasar cualquier tipo de view holder
abstract class BaseViewHolder<T> (itemView: View) : RecyclerView.ViewHolder(itemView) {
    //se encarga de bindear los elementos , agarra cada elemento y le pone los datos que requiere
    abstract fun bind(item: T)
}