package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    lateinit var etNombreDeUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var btnIniciarSesion: Button
    lateinit var tvError: TextView
    lateinit var cbRecordarUsuario: CheckBox

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
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        tvError = findViewById(R.id.tvError)


        btnIniciarSesion.setOnClickListener {
                val usuario = AppDataBase.getDataBase(applicationContext).usuarioDao().findByNombre(etNombreDeUsuario.text.toString())
                if (usuario != null && usuario.contraseña == etContraseña.text.toString()) {
                    tvError.text = ""
                        Toast.makeText(this, "Iniciaste sesión con éxito", Toast.LENGTH_SHORT).show()
                        if(cbRecordarUsuario.isChecked){
                            var preferenias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                            preferenias.edit().putString(resources.getString(R.string.nombre_usuario),usuario.nombre).apply()
                            preferenias.edit().putString(resources.getString(R.string.password_usuario),usuario.contraseña).apply()
                        }
                        StartMainActivity()
                } else {
                    tvError.text = "Este usuario no existe"
                }

        }
    }
    private fun StartMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}