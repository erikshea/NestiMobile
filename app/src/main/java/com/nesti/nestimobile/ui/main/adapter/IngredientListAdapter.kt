package com.nesti.nestimobile.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.IngredientRecipe
import kotlinx.android.synthetic.main.item_layout_ingredient.view.*

class IngredientListAdapter(
        private val ingredients: ArrayList<IngredientRecipe>
        ) : RecyclerView.Adapter<IngredientListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ingredientRecipe: IngredientRecipe) {
            itemView.textView_ingredient_name.text = ingredientRecipe.name
            itemView.textView_ingredient_quantity.text = ingredientRecipe.quantity.toString()
            itemView.textView_ingredient_unit.text = ingredientRecipe.unitName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_ingredient, parent,
                false
            )
        )

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(ingredients[position])

    fun addData(list: List<IngredientRecipe>) {
        ingredients.addAll(list)
    }
}