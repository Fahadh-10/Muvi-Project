package com.example.muviproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.model.HomeList
import com.example.muviproject.databinding.MusicListBinding
import com.example.muviproject.model.MediaItem
import com.squareup.picasso.Picasso

class MusicADTR(var movie: ArrayList<HomeList>) : RecyclerView.Adapter<MusicADTR.MediaViewHolder>() {

    private var mOnItemClickListeners: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onClick(mediaItem: ArrayList<MediaItem>?, position: Int)
    }

    fun setOnClickListeners(onItemClick: OnItemClickListeners) {
        mOnItemClickListeners = onItemClick
    }

    class MediaViewHolder(mBinding: MusicListBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val binding: MusicListBinding = mBinding
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(
            MusicListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movie.sumOf { it.mediaItems?.size ?: 0 }
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        var currentCount = 0
        for (item in movie) {
            val mediaItems = item.mediaItems ?: continue
            if (position >= currentCount && position < currentCount + mediaItems.size) {
                val mediaItem = mediaItems[position - currentCount]
                Picasso.get().load(mediaItem.imageUrl).into(holder.binding.movieIV)
                holder.binding.mediaCV.setOnClickListener {
                    mOnItemClickListeners?.onClick(item.mediaItems, position)
                }
                break
            }
            currentCount += mediaItems.size
        }

    }
}