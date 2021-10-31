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
        var minute = 0
        var hour = 0

        // time button
        binding.setTimeButton.setOnClickListener{
            var timePicker: DialogFragment = TimePickerFragment()
            timePicker.show(childFragmentManager, "time picker")
            hour = TimePickerFragment.getHour()
            minute = TimePickerFragment.getMinute()
        }

        // time formatting
        var minuteString = ""
        var hourString = ""
        if (minute < 10){
            minuteString = "0$minute"
        }
        time = "$hour:$minuteString"

        // when the add button is clicked, get information into strings to then add into database, switch screens
        binding.addButton.setOnClickListener {
            question = binding.addTitle.text.toString()
            yesMessage = binding.yesMessage.text.toString()
            noMessage = binding.noMessage.text.toString()
            if (question == "" || yesMessage == "" || noMessage == "" || time == ""){
                Toast.makeText(this.context, "All information must be filled out!", Toast.LENGTH_SHORT).show()
            } else {
                // add to database
                val toDoModel = ToDoModel(question, yesMessage, noMessage, time)
                val dataBaseHelper = DataBaseHelper(this.context)
                val success = dataBaseHelper.addOne(toDoModel)
                if (success) {
                    Toast.makeText(this.context, "New entry created!", Toast.LENGTH_SHORT).show()
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