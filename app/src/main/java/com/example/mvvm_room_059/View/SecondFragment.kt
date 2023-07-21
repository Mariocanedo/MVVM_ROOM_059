package com.example.mvvm_room_059.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvm_room_059.Modelo.Model.Task
import com.example.mvvm_room_059.R
import com.example.mvvm_room_059.ViewModel.TaskViewModel
import com.example.mvvm_room_059.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel : TaskViewModel by activityViewModels()
    private var idTask: Int=0
    private var taskSelected : Task? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.selectedItem().observe(viewLifecycleOwner, {
            it?.let { selectedTask ->

                binding.etTitle.setText(selectedTask.title)
                binding.etDate.setText(selectedTask.date)
                binding.etDescription.setText(selectedTask.descripcion)
                binding.etPriority.setText(selectedTask.priority.toString())
                binding.cbStatenew.isChecked = selectedTask.state
                idTask = selectedTask.id
                taskSelected = selectedTask


            }


        })

        // button para guardar

        binding.btnsave.setOnClickListener{

            saveData()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }




    }

        // funcion para guardar tareas


        private fun saveData(){
            val title = binding.etTitle.text.toString()
            val descripcion = binding.etDescription.text.toString()
            val date = binding.etDate.text.toString()
            val priority = binding.etPriority.text.toString().toInt()
            val state= binding.cbStatenew.isChecked

            // SE VALIDAD ALGUNOS CAMPOS
            if(title.isEmpty() || descripcion.isEmpty() || date.isEmpty()) {
                Toast.makeText(context," Debe añadir los datos",Toast.LENGTH_LONG).show()
            }
            else {
                // SI NO HAY NADA CREA UNA NUEVA ACTIVIDAD
                // SI HAY ALGO EN LA BASE DE DATOS LA ACTUALIZA
                if(idTask==0) {
                    val newTask = Task( title = title,
                        descripcion = descripcion,
                        date = date,
                        priority = priority,
                        state = state)
                    viewModel.inserTask(newTask)
                    Toast.makeText(context," DATOS GUARDADOS CORRECTAMENTE",Toast.LENGTH_LONG).show()

                }else{
                    val newTask1 = Task(
                        // debemos incluir el id que viene del
                        // observer para saber cual actualizar
                        id= idTask,
                        title = title,
                        descripcion = descripcion,
                        date = date,
                        priority = priority,
                        state = state)
                    viewModel.updateTask(newTask1)
                    Toast.makeText(context," DATOS ACTUALIZADOS",Toast.LENGTH_LONG).show()
                }
            }
        }









    override fun onDestroyView() {
        super.onDestroyView()
        // actualizar view model para destruir
       viewModel.selected(null)
    }

    }