package com.example.muviproject.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.adapter.viewholder.BannerViewHolder
import com.example.muviproject.adapter.viewholder.CategoriesViewHolder
import com.example.muviproject.adapter.viewholder.MediaItemViewHolder
import com.example.muviproject.adapter.viewholder.PopularMoviesViewHolder
import com.example.muviproject.adapter.viewholder.TopShowsViewHolder
import com.example.muviproject.adapter.viewholder.TrendingMusicViewHolder
import com.example.muviproject.model.HomeList

class HomeListAdapter(
    private var homeList: ArrayList<HomeList>,
    val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListeners: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun bannerItemClickListener(bannerUrl: String, position: Int)
        fun mediaItemClickListener(mediaUrl: String, position: Int)
        fun categoryItemClickListener(category: String, position: Int)
    }

    fun setOnClickListeners(onItemClick: OnItemClickListeners) {
        this.onItemClickListeners = onItemClick
    }

    fun updateData(newHomeList: List<HomeList>) {
        homeList.clear()
        homeList.addAll(newHomeList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return HomeList.HomeListType.getHomeListViewType(homeList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (HomeList.HomeListType.getByPosition(viewType)) {
            HomeList.HomeListType.CATEGORIES -> CategoriesViewHolder.getViewHolder(parent)
            HomeList.HomeListType.BANNER -> BannerViewHolder.getViewHolder(parent)
            HomeList.HomeListType.RECOMMENDED_MOVIES -> MediaItemViewHolder.getViewHolder(parent)
            HomeList.HomeListType.POPULAR_MOVIES -> PopularMoviesViewHolder.getViewHolder(parent)
            HomeList.HomeListType.TOP_SHOWS -> TopShowsViewHolder.getViewHolder(parent)
            HomeList.HomeListType.TRENDING_IN_MUSIC -> TrendingMusicViewHolder.getViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoriesViewHolder -> holder.configureCell(homeList[position], onItemClickListeners, context)
            is BannerViewHolder -> holder.configureCell(homeList, onItemClickListeners, context)
            is MediaItemViewHolder -> holder.configureCell(homeList, onItemClickListeners, context)
            is PopularMoviesViewHolder -> holder.configureCell(homeList, onItemClickListeners, context)
            is TopShowsViewHolder -> holder.configureCell(homeList, onItemClickListeners, context)
            is TrendingMusicViewHolder -> holder.configureCell(homeList, onItemClickListeners, context)
        }
    }
}