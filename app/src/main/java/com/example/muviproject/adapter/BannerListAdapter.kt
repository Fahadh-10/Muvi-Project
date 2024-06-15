package com.example.muviproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.databinding.BannerListItemBinding
import com.example.muviproject.model.MediaItem
import com.squareup.picasso.Picasso
import java.util.ArrayList

class BannerListAdapter(private val banners: ArrayList<MediaItem>, private val context: Context) : RecyclerView.Adapter<BannerListAdapter.MiddleAdsViewHolder>() {

    private var mOnItemClickListener: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onItemClick(bannerUrl: String, videoUrl: String?, position: Int)
    }

    fun setOnClickListeners(onItemClick: OnItemClickListeners) {
        mOnItemClickListener = onItemClick
    }

    class MiddleAdsViewHolder(mBinding: BannerListItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val binding: BannerListItemBinding = mBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiddleAdsViewHolder {
        return MiddleAdsViewHolder(BannerListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    override fun onBindViewHolder(holder: MiddleAdsViewHolder, position: Int) {
        val banner = banners[position]
        if (banner.imageUrl.isNotEmpty()) {
            holder.binding.bannerIV.visibility = View.VISIBLE
            Picasso.get().load(banner.imageUrl).into(holder.binding.bannerIV)
        } else {
            holder.binding.bannerIV.visibility = View.GONE
        }

        holder.binding.bannerIV.setOnClickListener {
            mOnItemClickListener?.onItemClick(banner.imageUrl, banner.videoUrl, position)
        }
    }
}
