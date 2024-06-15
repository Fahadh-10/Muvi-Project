package com.example.muviproject.model

import java.io.Serializable

data class HomeList(
    val homeListingType: String,
    val title: String,
    val banners: ArrayList<String>? = null,
    val categories: ArrayList<String>? = null,
    val mediaItems: ArrayList<MediaItem>? = null,
    var isSelected: Boolean = false
) : Serializable {
    enum class HomeListType(val type: String) {
        CATEGORIES("CATEGORIES"),
        BANNER("BANNER"),
        RECOMMENDED_MOVIES("RECOMMENDED MOVIES"),
        POPULAR_MOVIES("POPULAR MOVIES"),
        TOP_SHOWS("TOP SHOWS"),
        TRENDING_IN_MUSIC("TRENDING IN MUSIC");

        companion object {
            fun getHomeListViewType(homeList: HomeList): Int {
                return when (homeList.getHomeListType()) {
                    CATEGORIES -> CATEGORIES.ordinal
                    BANNER -> BANNER.ordinal
                    RECOMMENDED_MOVIES -> RECOMMENDED_MOVIES.ordinal
                    POPULAR_MOVIES -> POPULAR_MOVIES.ordinal
                    TOP_SHOWS -> TOP_SHOWS.ordinal
                    TRENDING_IN_MUSIC -> TRENDING_IN_MUSIC.ordinal

                }
            }

            fun getByPosition(value: Int): HomeListType {
                return values().firstOrNull { it.ordinal == value } ?: BANNER
            }
        }
    }

    fun getHomeListType(): HomeListType {
        return HomeListType.valueOf((homeListingType ?: "").uppercase())
    }
}

data class MediaItem(
    val imageUrl: String,
    val videoUrl: String?,
) : Serializable
