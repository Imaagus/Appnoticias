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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appinashi.fragmentos.FragmentoBtns
import com.example.appinashi.fragmentos.FragmentoBtnsInterfaz

class ListadoNoticias : AppCompatActivity(), FragmentoBtnsInterfaz {
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
        supportActionBar!!.title = resources.getString(R.string.categoriaNoticias)
        val primerFragmento = supportFragmentManager
            .findFragmentById(R.id.contenedor_primer_fragmento) as? FragmentoBtns
        primerFragmento?.listener = this
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
    override fun mostrarContenido(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_segundo_fragmento, fragment)
            .addToBackStack(null)
            .commit()
    }
}