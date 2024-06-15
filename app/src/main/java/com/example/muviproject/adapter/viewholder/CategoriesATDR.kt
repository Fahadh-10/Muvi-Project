package com.example.muviproject.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.R
import com.example.muviproject.databinding.CategoryListBinding
import com.example.muviproject.model.HomeList

class CategoriesATDR(private val categories: ArrayList<HomeList>) : RecyclerView.Adapter<CategoriesATDR.CategoryViewHolder>() {

    private var onItemClickListeners: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onClick(category: String, position: Int)
    }

    fun setOnClickListeners(onItemClick: OnItemClickListeners) {
        this.onItemClickListeners = onItemClick
    }

    class CategoryViewHolder(val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position].title
        holder.binding.categoryTV.text = category
        if (categories[position].isSelected) {
            holder.binding.categoryTV.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.selected_bg)
        } else {
            holder.binding.categoryTV.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.unselected_bg)
        }
        holder.binding.categoryTV.setOnClickListener {
            categories.forEachIndexed { index, homeList ->
                homeList.isSelected = index == position
            }
            notifyDataSetChanged()
            onItemClickListeners?.onClick(category, position)
        }

    }

    init {
        if (categories.isNotEmpty()) {
            categories[0].isSelected = true
        }
    }
}


