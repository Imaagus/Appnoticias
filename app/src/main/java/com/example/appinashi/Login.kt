package com.example.appinashi

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appinashi.dataBase.AppDataBase

class Login : AppCompatActivity() {
    lateinit var etNombreDeUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var btnIniciarSesion: Button
    lateinit var tvError: TextView
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var toolBar: Toolbar

    companion object {
        const val MY_CHANNEL_ID = "myChannel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Crear el canal de notificacion
        CreateChannel()

        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = resources.getString(R.string.iniciar_sesion)

        etNombreDeUsuario = findViewById(R.id.etNombreDeUsuario)
        etContraseña = findViewById(R.id.etContraseña)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        tvError = findViewById(R.id.tvError)

        btnIniciarSesion.setOnClickListener {
            val usuario = AppDataBase.getDataBase(applicationContext).usuarioDao().findByNombre(etNombreDeUsuario.text.toString())
            if (usuario != null && usuario.contraseña == etContraseña.text.toString()) {
                tvError.text = ""
                if (cbRecordarUsuario.isChecked) {
                    val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                    with(preferencias.edit()) {
                        putString(resources.getString(R.string.nombre_usuario), usuario.nombre)
                        putString(resources.getString(R.string.password_usuario), usuario.contraseña)
                        apply()
                    }
                    createNotification()
                }
                StartMainActivity()
            } else {
                tvError.text = "Este usuario no existe"
            }
        }
    }


    private fun StartMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_volver) {
            intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    // Crear el canal de notificacion
    fun CreateChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MyChannel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Recordatorio de usuario"
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Crear la notificación
    fun createNotification() {
        val builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setContentTitle("Recordar usuario activado")
            .setContentText("Usted ha activado correctamente la función de recordar usuario")
            .setSmallIcon(R.drawable.logo_noticias)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MyChannel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Recordatorio de usuario"
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
        } else {
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(1, builder.build())
        }
    }
}
