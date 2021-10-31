package com.main.nagapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.nagapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private var cardlist = ArrayList<Card>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var toDoItems:HashMap<Int, ToDoModel> = HashMap()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // THIS IS HOW TO GET THE ITEMS
        val dataBaseHelper = DataBaseHelper(this.context)
        toDoItems = dataBaseHelper.showAll()
        for (key in toDoItems.keys){
            val item = toDoItems.get(key)
            // Toast.makeText(this.context, item.toString(), Toast.LENGTH_SHORT).show() DEBUG
        }

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    // update the layout
    private fun setAdapter() {
        var adapter = RecyclerAdapter(cardlist)
        var recyclerView: RecyclerView = binding.rView
        var layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

    }

    // adding everything in the database to the cards
    private fun databaseOntoCards(){
        for (key in toDoItems.keys){
            val item = toDoItems[key]
            val question = item?.getQuestion()
            val time = item?.getTime()
            val no = item?.getYesMessage()
            val yes = item?.getNoMessage()
            val newCard = Card(question, yes, no, time)
            cardlist.add(newCard)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseOntoCards()
        setAdapter();

        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}