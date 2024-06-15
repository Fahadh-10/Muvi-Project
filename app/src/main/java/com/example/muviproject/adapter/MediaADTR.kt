package com.example.muviproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.model.HomeList
import com.example.muviproject.databinding.MediaListBinding
import com.example.muviproject.model.MediaItem
import com.squareup.picasso.Picasso

class MediaADTR(var movie: ArrayList<HomeList>) : RecyclerView.Adapter<MediaADTR.MediaViewHolder>() {

    private var mOnItemClickListeners: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onClick(mediaItem: ArrayList<MediaItem>?, position: Int)
    }

    fun setOnClickListeners(onItemClick: OnItemClickListeners) {
        mOnItemClickListeners = onItemClick
    }

    class MediaViewHolder(mBinding: MediaListBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val binding: MediaListBinding = mBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(
            MediaListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movie.sumBy { it.mediaItems?.size ?: 0 }
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        var currentCount = 0
        for (item in movie) {
            val mediaItems = item.mediaItems ?: continue
            if (position >= currentCount && position < currentCount + mediaItems.size) {
                val relativePosition = position - currentCount
                val mediaItem = mediaItems[relativePosition]
                holder.binding.titleTV.text = item.title
                Picasso.get().load(mediaItem.imageUrl).into(holder.binding.movieIV)

                holder.binding.mediaCV.setOnClickListener {
                    mOnItemClickListeners?.onClick(item.mediaItems, relativePosition)
                }
                break
            }
            currentCount += mediaItems.size
        }
    }
}