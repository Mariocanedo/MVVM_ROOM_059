package com.example.mvvm_room_059.Modelo.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task:: class], version = 1)
 abstract class TaskDataBase :RoomDatabase() {


    // conexion dao con la base de datos
    abstract  fun getTaskDao(): TaskDao


    // NOSE PUEDE ISNTANCIAR ES ABSTRACTO
    // COMPANION OBJECT EXPONE UN OBJETO SIN INSTANCIAR LA CLASE
    companion object {
        // ESTA VARIABLE ESTE SIEMPRE DISPONIBLE
        @Volatile
        private var INSTANCE: TaskDataBase? = null


        // MAIN THREAD
        // BACK THREAD HILO SECUNDARIOS VOLATILE HACE QUE SE EJECUTE DONDE ESTE DISPONIBLE
        // TAREAS ASINCRONAS


        // CONTEXTO DONDE ESTAMOS EJECUTANDO LOS PROCESOS
        // MUCHAS FORMAS DE EJECUTAR EL CONTEXTO
        fun getDatabase(context: Context): TaskDataBase {
            val tempInntance = INSTANCE
            // ES DISTINTO A NULL
            if (tempInntance != null) {

                return tempInntance
            }

            // LLAMA A UN METODO Y LO SINCRONIZA PARA INSTANCIAR
            synchronized(this) {
                // CLASE DE ROOM ES EL CREADOR DE LA INSTANCIA DE LA BASE DE DATOS
                val instance = Room.databaseBuilder(
                    // la bade datos sea una para toda la app
                    context.applicationContext,
                    // NOMBRE DEL ARCHIVO QUE CONTIENE LA BASE DE DATO
                    TaskDataBase::class.java,
                    "TaskEntity"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}