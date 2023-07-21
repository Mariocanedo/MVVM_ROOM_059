package com.example.mvvm_room_059.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvm_room_059.Modelo.Model.Task
import com.example.mvvm_room_059.R
import com.example.mvvm_room_059.ViewModel.TaskViewModel
import com.example.mvvm_room_059.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        val newTask= Task(

            title="BD59",
            descripcion = "PRUEBA 059 20:31",
            date="20/07/2023",
            priority = 7,
            state = true

        )

         viewModel.inserTask(newTask)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}