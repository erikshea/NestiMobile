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

/**
 * adapter for a recyclerview that shows a recipe's list of ingredients
 * @param ingredients list of IngredientRecipe entities
 */
class IngredientListAdapter( private val ingredients: ArrayList<IngredientRecipe> )
    : RecyclerView.Adapter<IngredientListAdapter.DataViewHolder>() {

    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // DAO for sqlite database holding shopping list
        val ingredientDao = IngredientDao(itemView.context)

        /**
         * binds data to an individual recyclerview line
         */
        fun bind(ingredientRecipe: IngredientRecipe) {
            itemView.textView_ingredient_name.text = ingredientRecipe.name.capitalize()
            itemView.textView_ingredient_quantity.text = ingredientRecipe.quantity.toString()
            itemView.textView_ingredient_unit.text = ingredientRecipe.unitName

            if (ingredientDao.findById(ingredientRecipe.idIngredient) == null) {
                // if ingredient isn't in shopping list, button is a plus sign
                itemView.textView_ingredient_add.text = "";
            } else {
                // else, button is a plus sign
                itemView.textView_ingredient_add.text = "\uF146";
            }

            itemView.textView_ingredient_add.setOnClickListener {
                if (ingredientDao.findById(ingredientRecipe.idIngredient) == null) {
                    // if ingredient isn't in shopping list
                    ingredientDao.saveOrUpdate(ingredientRecipe.getIngredient())
                    itemView.textView_ingredient_add.text = "\uF146";
                } else {
                    ingredientDao.delete(ingredientRecipe.getIngredient())
                    itemView.textView_ingredient_add.text = "";
                }
            }
        }
    }

    /**
     * sets up a  view holder which will bind values to the recyclerview lines
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_ingredient, parent,
                false
            )
        )

    /**
     * Determines recyclerview size
     */
    override fun getItemCount(): Int = ingredients.size

    /**
     * Will call our view holder's bind method when a line is displayed
     */
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(ingredients[position])

    /**
     * Sets up the list of entities from which the recyclerView is built
     */
    fun addData(list: List<IngredientRecipe>) {
        ingredients.addAll(list)
        notifyDataSetChanged()
    }
}