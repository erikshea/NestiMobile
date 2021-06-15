package com.nesti.nestimobile.ui.main.adapter

import IngredientDao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.ui.main.adapter.base.BaseDataViewHolder
import com.nesti.nestimobile.ui.main.adapter.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_ingredient.view.textView_ingredient_name
import kotlinx.android.synthetic.main.item_shopping_list.view.*

/**
 * adapter for a recyclerview that shows a shopping list's ingredients
 * @param ingredients list of Ingredient entities
 */
class ShoppingListAdapter(
        val ingredients: ArrayList<Ingredient>
        ) : BaseRecyclerViewAdapter<Ingredient, ShoppingListAdapter.DataViewHolder>(ingredients) {

    /**
     * sets up views for recyclerview lines
     * @param itemView view object for line
     */
    class DataViewHolder(itemView: View) : BaseDataViewHolder<Ingredient>(itemView) {
        private val ingredientDao = IngredientDao(itemView.context)

        override fun bind(ingredient: Ingredient) {
            itemView.textView_ingredient_name.text = ingredient.name

            itemView.checkbox_shopping_list.isChecked = ingredient.isChecked == 1

            itemView.checkbox_shopping_list.setOnCheckedChangeListener { _, isChecked ->
                //change checked status, update entity in data source
                ingredient.isChecked = isChecked.compareTo(false)
                ingredientDao.saveOrUpdate(ingredient)
            }
        }
    }

    /**
     * sets up a  view holder which will bind values to the recyclerview lines
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping_list, parent,
                false
            )
        )
}