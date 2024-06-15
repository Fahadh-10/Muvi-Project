package com.example.muviproject.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.adapter.HomeListAdapter
import com.example.muviproject.databinding.CategoryItemViewHolderBinding
import com.example.muviproject.model.HomeList

class CategoriesViewHolder(private val binding: CategoryItemViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun configureCell(
        homeList: HomeList,
        onItemClickListeners: HomeListAdapter.OnItemClickListeners?,
        context: Context
    ) {
        homeList.categories?.let { categories ->
            val categoryItems = categories.map { HomeList(homeList.homeListingType, it, isSelected = false) }
            val adapter = CategoriesATDR(ArrayList(categoryItems))
            binding.categoryRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.categoryRV.adapter = adapter
            adapter.setOnClickListeners(object : CategoriesATDR.OnItemClickListeners {
                override fun onClick(category: String, position: Int) {
                    onItemClickListeners?.categoryItemClickListener(category, position)
                }
            })
        }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup): CategoriesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CategoryItemViewHolderBinding.inflate(layoutInflater, parent, false)
            return CategoriesViewHolder(binding)
        }
    }
}
