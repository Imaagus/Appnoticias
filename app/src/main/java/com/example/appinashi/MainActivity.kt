package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Looper
import com.example.appinashi.apiRequest.PostEndPoint
import com.example.appinashi.apiRequest.RetroFitClient

class MainActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var tvResetService: TextView
    private lateinit var tvDescripcion: TextView
    private val handler = Handler(Looper.getMainLooper())

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
        supportActionBar?.title = resources.getString(R.string.tituloUltimasNoticias)
        tvResetService = findViewById(R.id.tvRestService)
        tvDescripcion = findViewById(R.id.tvDescrip)


        // Llamada a la API
        val accessKey = "1f196924ee3db7bcd5de96a6ba5945f0"
        val countries = "ar"
        val language = "es"
        val category = "general"

        val api = RetroFitClient.retrofit.create(PostEndPoint::class.java)
        val callPost = api.getPosts(accessKey, category, countries, language)

        // Ejecuta la llamada
        Thread {
            try{
                val response = callPost.execute()

                handler.post {
                    if (response.isSuccessful && response.body() != null) {
                        val posts = response.body()!!.data // Obtiene la lista de Post desde la respuesta
                        if (posts.isNotEmpty()) {
                            val linearLayoutContent = findViewById<LinearLayout>(R.id.linearLayoutContent)
                            posts.forEach { post ->
                                val titleTextView = TextView(this@MainActivity)
                                titleTextView.text = "● ${post.title}"
                                titleTextView.textSize = 22f
                                titleTextView.setPadding(16, 16, 16, 8)

                                val descriptionTextView = TextView(this@MainActivity)
                                descriptionTextView.text = post.description ?: "Descripción no disponible"
                                descriptionTextView.textSize = 16f
                                descriptionTextView.setPadding(16, 8, 16, 16)

                                linearLayoutContent.addView(titleTextView)
                                linearLayoutContent.addView(descriptionTextView)
                            }
                        } else {
                            Log.e("Response", "No hay posts disponibles.")
                            tvResetService.text = "No hay posts disponibles."
                        }
                    } else {
                        Log.e("Response", "Error en la respuesta: ${response.code()} - ${response.message()}")
                        tvResetService.text = "Error: ${response.code()} - ${response.message()}"
                    }
                }
            } catch(e: Exception){
                handler.post{
                    Log.e("ERROR", e.message ?: "")
                    tvResetService.text = "ERROR : ${e.message}"
                }
            }
        }.start()
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
            cerrarSesion()
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.remove(resources.getString(R.string.nombre_usuario))
        editor.remove(resources.getString(R.string.password_usuario))
        editor.apply()
    }
}
