package com.nesti.nestimobile.ui.main.adapter.base

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * sets up views for recyclerview lines
 * @param TEntity entity class from which to build lines
 * @param itemView view object for lines
 */
abstract class BaseDataViewHolder<TEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val resources: Resources = itemView.context.resources
    protected val packageName: String = itemView.context.packageName

    /**
     * binds data to an individual recyclerview line
     */
    abstract fun bind(entity: TEntity)
}