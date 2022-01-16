package com.example.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//bindeamos un adapter
abstract class BaseContactHolder<T> (itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}