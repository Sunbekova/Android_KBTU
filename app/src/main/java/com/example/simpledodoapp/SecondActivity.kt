package com.example.simpledodoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpledodoapp.databinding.ActivitySecondBinding
import com.example.simpledodoapp.model.Pizza
import android.os.Parcelable





class SecondActivity : AppCompatActivity() {

    companion object {
        const val KEY_RESULT = "KeyResult"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pizza: Pizza? = intent.getParcelableExtra(SecondActivity.KEY_RESULT)


        if (pizza != null) {
            displayPizzaDetails(pizza)
        } else {
            // Обработка случая, когда объект Pizza не был передан
        }
    }

    private fun displayPizzaDetails(pizza: Pizza) {
        // Установите данные пиццы в соответствующие представления
        binding.pizzaImg.setImageResource(pizza.imageRes)
        binding.pizzaName.text = pizza.name
        binding.pizzaDescription.text = pizza.shortDescription
        // Другие детали пиццы, которые вы хотите отобразить
    }

}

