package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    lateinit var etNombreDeUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var btnIniciarSesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNombreDeUsuario = findViewById(R.id.etNombreDeUsuario)
        etContraseña = findViewById(R.id.etContraseña)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            Thread {
                val usuario = AppDataBase.getDataBase(applicationContext).usuarioDao().findByNombre(etNombreDeUsuario.text.toString())
                if (usuario != null && usuario.contraseña == etContraseña.text.toString()) {
                    runOnUiThread {
                        Toast.makeText(this, "Iniciaste sesión con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }.start()
        }

    }
}