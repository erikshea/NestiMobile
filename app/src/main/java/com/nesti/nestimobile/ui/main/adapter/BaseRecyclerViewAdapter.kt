package com.nesti.nestimobile.ui.main.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag


abstract class BaseRecyclerViewAdapter<TEntity> ( private val entities: ArrayList<TEntity> )
    : RecyclerView.Adapter<BaseRecyclerViewAdapter.DataViewHolder<TEntity>>()
{
    abstract class DataViewHolder<TEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected val res: Resources = itemView.context.resources
        private val packageName = itemView.context.packageName

        abstract fun bind(entity: TEntity)
    }

    /**
     * Determines recyclerview size
     */
    override fun getItemCount(): Int = entities.size

    /**
     * Will call our view holder's bind method when a line is displayed
     */
    override fun onBindViewHolder(holder: DataViewHolder<TEntity>, position: Int) =
        holder.bind(entities[position])

    /**
     * Sets up the list of entities from which the recyclerView is built
     */
    fun addData(list: List<TEntity>) {
        entities.addAll(list)
    }
}