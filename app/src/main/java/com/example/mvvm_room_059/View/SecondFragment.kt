package com.example.mvvm_room_059.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
               idTask = selectedTask.id
               taskSelected= selectedTask


           }


       })


        super.onDestroyView()
      //  _binding = null
    }
}