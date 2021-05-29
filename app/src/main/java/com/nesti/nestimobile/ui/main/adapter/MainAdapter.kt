package com.nesti.nestimobile.ui.main.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout_tag.view.*
import kotlin.coroutines.coroutineContext

class MainAdapter(
        private val tags: ArrayList<Tag>
        ) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val res: Resources = itemView.context.resources
        private val packageName: String = itemView.context.packageName

        fun bind(tag: Tag) {
            itemView.buttonCategory.text = tag.name
            val colorId: Int = res.getIdentifier("category_${tag.idTag}", "color", packageName)

            itemView.buttonCategory.setBackgroundColor( ContextCompat.getColor(itemView.context, colorId));
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            DataViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_layout_tag, parent,
                            false
                    )
            )

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
            holder.bind(tags[position])

    fun addData(list: List<Tag>) {
        tags.addAll(list)
    }
}