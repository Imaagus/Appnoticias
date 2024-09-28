package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Inicio : AppCompatActivity() {
    lateinit var btnRegistrarse: Button
    lateinit var btnIniciarSesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),"")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario),"")

        if(usuarioGuardado!="" && passwordGuardado!=""){
            if(usuarioGuardado!=null){
                StartMainActivity()
            }
        }

        btnRegistrarse.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    private fun StartMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}