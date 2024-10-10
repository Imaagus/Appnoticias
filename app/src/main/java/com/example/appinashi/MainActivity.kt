package com.example.appinashi

import android.content.Intent
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
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var toolBar: Toolbar
    private lateinit var tvResetService : TextView
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
        supportActionBar!!.title = resources.getString(R.string.titulo)

        val api = RetroFitClient.retrofit.create(PostEndPoint::class.java)
        val callPost = api.getPosts()

        callPost.enqueue(object : retrofit2.Callback<List<Post>>
        {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val post = response.body()
                if(post != null){
                    tvResetService = findViewById(R.id.tvRestService)
                    tvResetService.text = post.toString()
                    Log.d("Response", post.toString())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("ERROR", t.message ?: "")
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemListado){
            val intent = Intent(this, ListadoNoticias::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}