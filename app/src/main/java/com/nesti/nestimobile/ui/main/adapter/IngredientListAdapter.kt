package com.nesti.nestimobile.ui.main.adapter

import IngredientDao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.ui.main.adapter.base.BaseDataViewHolder
import com.nesti.nestimobile.ui.main.adapter.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_layout_ingredient.view.*

/**
 * adapter for a recyclerview that shows a recipe's list of ingredients
 * @param ingredients list of IngredientRecipe entities
 */
class IngredientListAdapter( private val ingredients: ArrayList<IngredientRecipe> )
    : BaseRecyclerViewAdapter<IngredientRecipe, IngredientListAdapter.DataViewHolder>(ingredients) {

    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) :  BaseDataViewHolder<IngredientRecipe>(itemView) {
        // DAO for sqlite database holding shopping list
        private val ingredientDao = IngredientDao(itemView.context)

        /**
         * binds data to an individual recyclerview line
         */
        override fun bind(ingredientRecipe: IngredientRecipe) {
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
}