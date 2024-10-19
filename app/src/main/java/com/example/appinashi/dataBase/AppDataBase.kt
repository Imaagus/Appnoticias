package com.example.appinashi.dataBase



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Usuario::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    companion object {
        private var INSTANCIA: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(context,
                        AppDataBase::class.java,"usuario_database").allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCIA!!
        }
    }
}

