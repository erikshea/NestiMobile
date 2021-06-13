package com.nesti.nestimobile.ui.main.adapter.base

import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<TEntity, TViewHolder: BaseDataViewHolder<TEntity>> (
        private val entities: ArrayList<TEntity>
    )
    : RecyclerView.Adapter<TViewHolder>()
{
    /**
     * Determines recyclerview size
     */
    override fun getItemCount(): Int = entities.size

    /**
     * Will call our view holder's bind method when a line is displayed
     */
    override fun onBindViewHolder(holder: TViewHolder, position: Int) =
        holder.bind(entities[position])

    /**
     * Adds to the list of entities from which the recyclerView is built
     */
    fun addData(list: List<TEntity>) {
        entities.addAll(list)
        notifyDataSetChanged() // notify observers
    }

    /**
     * Sets the list of entities from which the recyclerView is built
     */
    fun setData(list: List<TEntity>) {
        entities.clear()
        addData(list)
    }
}