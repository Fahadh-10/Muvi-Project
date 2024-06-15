package com.example.muviproject.adapter.viewholder

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muviproject.adapter.HomeListAdapter
import com.example.muviproject.adapter.BannerListAdapter
import com.example.muviproject.model.HomeList
import com.example.muviproject.databinding.BannerViewHolderBinding
import com.example.muviproject.model.MediaItem
import java.util.Timer
import java.util.TimerTask

class BannerViewHolder(private val binding: BannerViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    private var timer: Timer? = null

    fun configureCell(
        homeList: ArrayList<HomeList>,
        onItemClickListeners: HomeListAdapter.OnItemClickListeners?,
        context: Context,
    ) {
        val filteredList = homeList.filter { it.homeListingType == HomeList.HomeListType.BANNER.name } as ArrayList<HomeList>
        val banners = ArrayList<MediaItem>()
        filteredList.forEach { home ->
            home.mediaItems?.filterTo(banners) { !it.imageUrl.isNullOrEmpty() }
        }
        val bannerListAdapter = BannerListAdapter(banners, context)
        binding.middleAdsVP.adapter = bannerListAdapter
        bannerListAdapter.setOnClickListeners(object : BannerListAdapter.OnItemClickListeners {
            override fun onItemClick(bannerUrl: String, videoUrl: String?, position: Int) {
                val homeListPosition = getHomeListPosition(position, homeList)
                if (videoUrl != null) {
                    onItemClickListeners?.bannerItemClickListener(videoUrl, homeListPosition)
                }
            }
        })

        if (banners.isNotEmpty()) {
            binding.pageIndicatorDI.attachTo(binding.middleAdsVP)
            startAutoSwipe()
        }
    }

    /**
     * Starts auto-swiping the ViewPager to display banner items automatically.
     */
    private fun startAutoSwipe() {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            var currentItem = binding.middleAdsVP.currentItem
            currentItem += 1
            if (currentItem >= (binding.middleAdsVP.adapter?.itemCount ?: 0)) {
                currentItem = 0
            }
            binding.middleAdsVP.currentItem = currentItem
        }
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 5000, 5000)
    }

    companion object {
        fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            return BannerViewHolder(
                BannerViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    /**
     * Retrieves the position of the HomeList item corresponding to the given adapter position.
     */
    private fun getHomeListPosition(adapterPosition: Int, homeList: ArrayList<HomeList>): Int {
        var currentCount = 0
        for ((index, home) in homeList.withIndex()) {
            val itemCount = home.mediaItems?.size ?: 0
            if (adapterPosition >= currentCount && adapterPosition < currentCount + itemCount) {
                return index
            }
            currentCount += itemCount
        }
        return -1
    }
}

