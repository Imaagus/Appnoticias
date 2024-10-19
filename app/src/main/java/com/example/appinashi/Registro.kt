package com.example.appinashi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.util.PatternsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appinashi.dataBase.AppDataBase
import com.example.appinashi.dataBase.Usuario


class Registro : AppCompatActivity() {

    lateinit var etUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var etConfirmContraseña: EditText
    lateinit var etEmail: EditText
    lateinit var btnCrearUsuario: Button
    lateinit var tvError: TextView
    lateinit var toolBar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etUsuario = findViewById(R.id.etUsuario)
        etEmail = findViewById(R.id.etEmail)
        etContraseña = findViewById(R.id.etContraseña)
        etConfirmContraseña = findViewById(R.id.etConfirmContraseña)
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario)
        tvError = findViewById(R.id.tvError)

        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.title = resources.getString(R.string.crear_usuario)

        btnCrearUsuario.setOnClickListener {
            if (etUsuario.text.toString().isEmpty() || etEmail.text.toString().isEmpty() || etContraseña.text.toString().isEmpty() || etConfirmContraseña.text.toString().isEmpty()) {
                tvError.text = "Por favor completar todos los datos"
            } else {
                if(PatternsCompat.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()){
                    if (etContraseña.text.toString() == etConfirmContraseña.text.toString()) {
                        tvError.text = ""

                        // Crear nuevo usuario
                        val nuevoUsuario = Usuario(nombre = etUsuario.text.toString(), email = etEmail.text.toString(), contraseña = etContraseña.text.toString())

                        // Agregar el usuario a la base de datos
                        AppDataBase.getDataBase(applicationContext).usuarioDao().insert(nuevoUsuario)

                        Toast.makeText(this, "Tu usuario fue creado con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        tvError.text = "Las contraseñas no coinciden"
                    }
                }else {
                    tvError.text = "El email no es valido"
                }
            }
        }

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