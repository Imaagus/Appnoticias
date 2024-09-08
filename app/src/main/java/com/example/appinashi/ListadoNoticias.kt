package com.example.appinashi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ListadoNoticias : AppCompatActivity() {
    lateinit var rvNoticias: RecyclerView
    lateinit var noticaAdapter: NoticiaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_noticias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvNoticias= findViewById(R.id.recyclerNot)
        noticaAdapter= NoticiaAdapter(getNoticias(),this)
        rvNoticias.adapter = noticaAdapter
    }
    private fun getNoticias(): MutableList<Noticia>{
        var examenes: MutableList<Noticia> = ArrayList()
        examenes.add(Noticia(1, "Deportes"))
        examenes.add(Noticia(2, "Comunicacion"))
        examenes.add(Noticia(3, "Musica"))
        examenes.add(Noticia(4, "Media"))
        examenes.add(Noticia(5, "Politicas"))
        examenes.add(Noticia(6, "Economicas"))
        examenes.add(Noticia(7, "Ultimas noticias"))
        return examenes
    }
}