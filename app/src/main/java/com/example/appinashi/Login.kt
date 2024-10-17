package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    lateinit var etNombreDeUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var btnIniciarSesion: Button
    lateinit var tvError: TextView
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.title = resources.getString(R.string.iniciar_sesion)

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_volver){
            intent = Intent(this,Inicio::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}