package com.example.mvvm_room_059.Modelo.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm_room_059.Modelo.Model.Task


@Dao
interface TaskDao {

    // Insertar una tarea tiene una estrategia si se repite el Id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)


    // Insertar listado de tareas
    @Insert
    suspend fun insertTMultipleTask(list:List<Task>)


    @Update
    suspend  fun updateTask(task: Task)


    @Delete
    suspend  fun deleteOneTask(task: Task)

    // para borrar todas las tareas
    @Query("DELETE FROM TASK_TABLE")
    suspend fun deletAll()


    // traer todas las tareas

    @Query("SELECT * FROM TASK_TABLE")
     fun getAllTask1() :LiveData<List<Task>>


    @Query("SELECT * FROM TASK_TABLE ORDER BY id ASC")
    fun getAllTask():LiveData<List<Task>>

    // traer por titulo
    @Query("SELECT * FROM TASK_TABLE WHERE  title=:title Limit 1")
    fun getTaskByRTitle(title : String) : LiveData<Task>

    // Traer por id
    @Query("SELECT * FROM TASK_TABLE WHERE  id=:id")

    fun getTaskById(id:Int):LiveData<Task>
}


