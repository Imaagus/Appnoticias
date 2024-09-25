package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ListadoNoticias : AppCompatActivity() {
    lateinit var rvNoticias: RecyclerView
    lateinit var noticaAdapter: NoticiaAdapter
    lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_noticias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_volver){
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}