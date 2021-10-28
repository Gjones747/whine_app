package com.main.nagapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.main.nagapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var question = ""
        var yesMessage = ""
        var noMessage = ""
        var time = ""
        // time button
        binding.setTimeButton.setOnClickListener{
            var timePicker: DialogFragment = TimePickerFragment()
            timePicker.show(childFragmentManager, "time picker")
            parentFragmentManager.setFragmentResultListener("requestKey",
                viewLifecycleOwner
            ) { _, bundle ->
                // We use a String here, but any type that can be put in a Bundle is supported
                val hour = bundle.getString("h")
                val minute = bundle.getString("m")
                // Do something with the result
                time = "$hour:$minute"
            }
        }
        // when the add button is clicked, get information into strings to then add into database, switch screens
        binding.addButton.setOnClickListener {
            question = binding.addTitle.getText().toString()
            yesMessage = binding.yesMessage.getText().toString()
            noMessage = binding.noMessage.getText().toString()
            if (question == "" || yesMessage == "" || noMessage == "" || time == ""){
                // play an animation that says user must fill out all boxes
            } else {
                // add to database
                val toDoModel = ToDoModel(question, yesMessage, noMessage, time)
                val dataBaseHelper = DataBaseHelper(this.context)
                val success = dataBaseHelper.addOne(toDoModel)
                if (success) {
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

            }
        }
        // when the quit button is clicked, don't get information, switch screens
        binding.quitButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}