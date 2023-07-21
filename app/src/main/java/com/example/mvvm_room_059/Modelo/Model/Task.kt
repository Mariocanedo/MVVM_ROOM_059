package com.example.mvvm_room_059.Modelo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


//  representa nuestra tabla
@Entity(tableName= "task_table")
data class Task (

        @PrimaryKey(autoGenerate = true)
        @NotNull
        val id: Int = 0,
        val title: String,
        val descripcion: String,
        val date : String,
        val priority: Int,
        val state :Boolean

)