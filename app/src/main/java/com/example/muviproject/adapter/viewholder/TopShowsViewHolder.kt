package com.example.muviproject.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.R
import com.example.muviproject.adapter.HomeListAdapter
import com.example.muviproject.adapter.MediaADTR
import com.example.muviproject.model.HomeList
import com.example.muviproject.databinding.MediaItemViewHolderBinding
import com.example.muviproject.helper.showToast
import com.example.muviproject.model.MediaItem

open class TopShowsViewHolder(private val binding: MediaItemViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var mAdapter : MediaADTR

    fun configureCell(
        homeList: ArrayList<HomeList>,
        onItemClickListeners: HomeListAdapter.OnItemClickListeners?,
        context: Context,
    ) {
        setUpListeners(context)
        val filteredList = homeList.filter { it.homeListingType == HomeList.HomeListType.TOP_SHOWS.name } as ArrayList<HomeList>
        binding.titleTV.text = HomeList.HomeListType.TOP_SHOWS.type
        binding.mediaRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        mAdapter = MediaADTR(filteredList)
        binding.mediaRV.adapter = mAdapter
        mAdapter.setOnClickListeners(object : MediaADTR.OnItemClickListeners {
            override fun onClick(mediaItem: ArrayList<MediaItem>?, position: Int) {
                mediaItem?.get(position)?.let { it.videoUrl?.let { it1 ->
                    onItemClickListeners?.mediaItemClickListener(
                        it1, position)
                } }
            }
        })
    }

    /**
     * Sets up click listeners for UI elements
     */
    private fun setUpListeners(context: Context) {
        binding.viewAllIV.setOnClickListener {
            context.showToast(context.getString(R.string.coming_soon))
        }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup): TopShowsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MediaItemViewHolderBinding.inflate(layoutInflater, parent, false)
            return TopShowsViewHolder(binding)
        }
    }
}