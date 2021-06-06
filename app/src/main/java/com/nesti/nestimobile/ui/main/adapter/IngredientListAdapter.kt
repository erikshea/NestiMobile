package com.nesti.nestimobile.ui.main.adapter

import IngredientDao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.data.model.IngredientRecipe
import kotlinx.android.synthetic.main.item_layout_ingredient.view.*

class IngredientListAdapter(
        private val ingredients: ArrayList<IngredientRecipe>
        ) : RecyclerView.Adapter<IngredientListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientDao = IngredientDao(itemView.context)

        fun bind(ingredientRecipe: IngredientRecipe) {
            itemView.textView_ingredient_name.text = ingredientRecipe.name.capitalize()
            itemView.textView_ingredient_quantity.text = ingredientRecipe.quantity.toString()
            itemView.textView_ingredient_unit.text = ingredientRecipe.unitName
            if (ingredientDao.findById(ingredientRecipe.idIngredient) == null) {
                itemView.textView_ingredient_add.text = "";
            } else {
                itemView.textView_ingredient_add.text = "\uF146";
            }

            itemView.textView_ingredient_add.setOnClickListener {
                if (ingredientDao.findById(ingredientRecipe.idIngredient) == null) {
                    ingredientDao.saveOrUpdate(ingredientRecipe.getIngredient())
                    itemView.textView_ingredient_add.text = "\uF146";
                } else {
                    ingredientDao.delete(ingredientRecipe.getIngredient())
                    itemView.textView_ingredient_add.text = "";
                }
            }
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