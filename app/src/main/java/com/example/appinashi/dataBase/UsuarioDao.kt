package com.example.appinashi.dataBase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario_entity")
    fun getAllUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuario_entity WHERE nombre = :nombre")
    fun findByNombre(nombre: String): Usuario?
}
