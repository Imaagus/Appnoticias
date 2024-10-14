package com.example.appinashi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var tvResetService: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = resources.getString(R.string.titulo)
        tvResetService = findViewById(R.id.tvRestService)

        tvResetService.text = "Cargando datos..."

        // Llamada a la API
        val accessKey = "1f196924ee3db7bcd5de96a6ba5945f0" //  clave de acceso
        val keywords = "tennis"
        val countries = "us,gb,de"

        val api = RetroFitClient.retrofit.create(PostEndPoint::class.java)
        val callPost = api.getPosts(accessKey, keywords, countries)

        // Ejecuta la llamada
        callPost.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful && response.body() != null) {
                    val posts = response.body()!!.data // Obtiene la lista de Post desde la respuesta
                    if (posts.isNotEmpty()) {
                        // Mostrar solo los titulos de las noticias
                        val titles = posts.joinToString("\n") { it.title }
                        tvResetService.text = titles
                        Log.d("Response", "Posts: $titles")
                    } else {
                        Log.e("Response", "No hay posts disponibles.")
                        tvResetService.text = "No hay posts disponibles."
                    }
                } else {
                    Log.e("Response", "Error en la respuesta: ${response.code()} - ${response.message()}")
                    tvResetService.text = "Error: ${response.code()} - ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("ERROR", t.message ?: "")
                tvResetService.text = "Error: ${t.message}"
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemListado) {
            val intent = Intent(this, ListadoNoticias::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.itemCerrarSesion) {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
