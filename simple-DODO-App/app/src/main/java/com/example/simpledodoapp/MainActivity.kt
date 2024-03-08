package com.example.simpledodoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.simpledodoapp.adapter.PizzaAdapter
import com.example.simpledodoapp.databinding.ActivityMainBinding
import com.example.simpledodoapp.model.PizzaDataSource
import com.example.simpledodoapp.model.Pizza


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val adapter = PizzaAdapter(
            onPizzaClick = {
                handlePizzaClick(it)
                //Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerview.adapter = adapter

        adapter.setData(PizzaDataSource.pizzaList)


        val searchView = findViewById<SearchView>(R.id.searchView)



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })

    }






    private fun handlePizzaClick(pizza: Pizza) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.KEY_RESULT, pizza)
        startActivity(intent)
    }

}