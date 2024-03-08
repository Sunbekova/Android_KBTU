package com.example.simpledodoapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledodoapp.databinding.EachItemBinding
import com.example.simpledodoapp.databinding.ItemComboBinding
import com.example.simpledodoapp.model.Pizza
import com.example.simpledodoapp.model.PizzaDataSource


class PizzaAdapter(
    private val onPizzaClick: (Pizza) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        private const val VIEW_TYPE_PIZZA = 1
        private const val VIEW_TYPE_COMBO = 2
    }



    private val pizzaList: ArrayList<Pizza> = ArrayList()



    fun setData(pizzas: ArrayList<Pizza>){
        pizzaList.clear()
        pizzaList.addAll(pizzas)

        //method for obnovlenya)) elements
        notifyDataSetChanged()
    }


    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            PizzaDataSource.pizzaList
        } else {
            PizzaDataSource.pizzaList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        setData(ArrayList(filteredList))
    }


    inner class PizzaViewHolder(
        private val binding: EachItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(pizza: Pizza){

            with(binding){

                //Glide.with()
                pizzaImg.setImageResource(pizza.imageRes)
                pizzaName.text = pizza.name
                pizzaDescription.text = pizza.shortDescription
                pizzaCost.text = pizza.cost

                root.setOnClickListener {
                    onPizzaClick(pizza)
                }

            }
        }
    }


    inner class ComboViewHolder(
        private val binding: ItemComboBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(pizza: Pizza){

            with(binding){

                //Glide.with()
                pizzaImg.setImageResource(pizza.imageRes)
                pizzaName.text = pizza.name
                pizzaDescription.text = pizza.shortDescription
                pizzaCost.text = pizza.cost
                attentionMark.text = pizza.attention_mark

                root.setOnClickListener {
                    onPizzaClick(pizza)
                }

            }
        }
    }


    //create view for each element
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_PIZZA -> {
                val binding = EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PizzaViewHolder(binding)
            }
            VIEW_TYPE_COMBO -> {
                val binding = ItemComboBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ComboViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    //count elements in list
    override fun getItemCount() = pizzaList.size

    //for call method from Viewholder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pizza = pizzaList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_PIZZA -> (holder as PizzaViewHolder).bind(pizza)
            VIEW_TYPE_COMBO -> (holder as ComboViewHolder).bind(pizza)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (pizzaList[position].isCombo) VIEW_TYPE_COMBO else VIEW_TYPE_PIZZA
    }

}