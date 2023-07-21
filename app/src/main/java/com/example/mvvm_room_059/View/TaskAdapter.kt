package com.example.mvvm_room_059.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_room_059.Modelo.Model.Task
import com.example.mvvm_room_059.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>() {


// lista de los datos

    private var  mlistTaskEntity = listOf<Task>()


    /**************************** PARA SELECCIONAR********************************/

    // un elemento selecionado para ocupar en la función

    private  val selectedTaskEntity = MutableLiveData<Task>()

 // funcion para selecionar

    fun selectedItem(): LiveData<Task> = selectedTaskEntity
    /****************************************************************************************/



    fun update(listTask: List<Task>){
        mlistTaskEntity= listTask
        notifyDataSetChanged()
    }






    inner class TaskVH(private  val binding :TaskItemBinding) :
            RecyclerView.ViewHolder(binding.root),

       View.OnClickListener{

          fun bind (task: Task){

              binding.tvTitle.text= task.title
              binding.tvDescription.text= task.descripcion
              binding.tvDate.text= task.date
              binding.cbState.isChecked= task.state
              binding.tvPrioridad.text= task.priority.toString()

              // activar el elemto clik dentro de la vista
              itemView.setOnClickListener(this)

          }

        override fun onClick(v: View?) {
            selectedTaskEntity.value= mlistTaskEntity[adapterPosition]
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
       return  TaskVH( TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = mlistTaskEntity.size

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val Task = mlistTaskEntity[position]
        holder.bind(Task)
    }


}