package com.example.appinashi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appinashi.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario_entity")
    fun getAllUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuario_entity WHERE nombre = :nombre")
    fun findByNombre(nombre: String): Usuario?
}
