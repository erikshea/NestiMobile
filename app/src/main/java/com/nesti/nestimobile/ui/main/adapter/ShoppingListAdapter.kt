package com.nesti.nestimobile.ui.main.adapter

import IngredientDao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Ingredient
import kotlinx.android.synthetic.main.item_layout_ingredient.view.*
import kotlinx.android.synthetic.main.item_layout_ingredient.view.textView_ingredient_name
import kotlinx.android.synthetic.main.item_layout_shopping_list.view.*

class ShoppingListAdapter(
        val ingredients: ArrayList<Ingredient>
        ) : RecyclerView.Adapter<ShoppingListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientDao = IngredientDao(itemView.context)

        fun bind(ingredient: Ingredient) {
            itemView.textView_ingredient_name.text = ingredient.name

            itemView.checkbox_shopping_list.isChecked = ingredient.isChecked == 1

            itemView.checkbox_shopping_list.setOnCheckedChangeListener { buttonView, isChecked ->
                ingredient.isChecked = isChecked.compareTo(false)
                ingredientDao.saveOrUpdate(ingredient)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_shopping_list, parent,
                false
            )
        )

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(ingredients[position])

    fun addData(list: List<Ingredient>) {
        ingredients.addAll(list)
    }
}