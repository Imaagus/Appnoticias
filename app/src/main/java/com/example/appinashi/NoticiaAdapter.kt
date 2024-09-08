package com.example.appinashi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoticiaAdapter (var noticias: MutableList<Noticia>,var  context: Context):
    RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>(){
    class NoticiaViewHolder(view: View): RecyclerView.ViewHolder(view){
        lateinit var txtTipoNot: TextView

        init {
            txtTipoNot= view.findViewById((R.id.tv_tipo))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia,parent,false)
        return NoticiaViewHolder(view)
    }

    override fun getItemCount()= noticias.size

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val item = noticias.get(position)
        holder.txtTipoNot.text= item.tipo
    }
}