package com.example.muviproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muviproject.model.HomeList
import com.example.muviproject.model.MediaItem
import java.util.ArrayList

class HomeViewModel : ViewModel() {

    private val _homeList = MutableLiveData<ArrayList<HomeList>>()
    val homeList: LiveData<ArrayList<HomeList>> get() = _homeList
    init {
        loadHomeList()
    }

    /**
     * This function will create an ArrayList of HomeList objects, each representing a different category of items
     * to display on the home screen, such as categories, banners, recommended movies, popular movies,
     * top shows, and trending music.
     */
    private fun loadHomeList() {
        val list = arrayListOf(
            HomeList(
                homeListingType = HomeList.HomeListType.CATEGORIES.name,
                title = HomeList.HomeListType.CATEGORIES.type,
                categories = arrayListOf(
                    "ALL", "MOVIES", "TV SHOWS", "MUSIC"
                )
            ),
            HomeList(
                homeListingType = HomeList.HomeListType.BANNER.name,
                title = HomeList.HomeListType.BANNER.type,
                mediaItems = arrayListOf(
                MediaItem("https://wallpaperaccess.com/full/1212755.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                MediaItem("https://image.tmdb.org/t/p/original/jIi99ZvvXCNwYxNmo7DEUb7I0xv.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                MediaItem("https://c4.wallpaperflare.com/wallpaper/810/33/235/movies-avatar-1680x1050-entertainment-movies-hd-art-wallpaper-preview.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                MediaItem("https://images4.alphacoders.com/551/551771.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                MediaItem("https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/01/Tom-Cruise-Sci-fi-Movies-Edge-of-Tomorrow-War-of-the-Worlds-Oblivion.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                MediaItem("https://c4.wallpaperflare.com/wallpaper/884/965/115/movies-flash-superman-wonder-woman-wallpaper-preview.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
            )
            ),
            HomeList(
                homeListingType = HomeList.HomeListType.RECOMMENDED_MOVIES.name,
                title = HomeList.HomeListType.RECOMMENDED_MOVIES.type,
                mediaItems = arrayListOf(
                    MediaItem("https://i.pinimg.com/originals/0e/39/d5/0e39d5956e5370dcd7fca84dcb641a58.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://cdn.prod.website-files.com/6009ec8cda7f305645c9d91b/6408f676b5811234c887ca62_top%20gun%20maverick-min.png", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/3ad58253131197.5928e7ec98295.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://i.pinimg.com/originals/86/2d/8e/862d8e2c5d7700db923ec5f51e77e088.png", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://images.sr.roku.com/idType/roku/context/global/id/13e63820177f5156b17ccfd31892cde8/images/gracenote/assets/p7931736_v_v8_ae.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://media.hollywood.com/images/l/faster_poster_dec23.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                )
            ),
            HomeList(
                homeListingType = HomeList.HomeListType.POPULAR_MOVIES.name,
                title = HomeList.HomeListType.POPULAR_MOVIES.type,
                mediaItems = arrayListOf(
                    MediaItem("https://i.pinimg.com/originals/77/7b/0a/777b0aa2357163ee6bfb67d568534e5a.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://cdn.traileraddict.com/content/walt-disney-pictures/pirates_caribbean4-6.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://www.tallengestore.com/cdn/shop/products/JohnWick-KeanuReeves-HollywoodEnglishActionMoviePoster-2_1eac59c5-8747-4ce2-937b-4b916be044cc.jpg?v=1649071607", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://www.themoviedb.org/t/p/original/70fWwkCYjsHPhPRUF9OydhbEkVC.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://tse4.mm.bing.net/th?id=OIP.SgMEGurjMaBhyOzMAEZfzgHaK_&pid=Api&P=0&h=180", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://i.insider.com/575f1dc191058426008c8b1a?width=750&format=jpeg&auto=webp", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
                )
            ),
            HomeList(
                homeListingType = HomeList.HomeListType.TOP_SHOWS.name,
                title = HomeList.HomeListType.TOP_SHOWS.type,
                mediaItems = arrayListOf(
                    MediaItem("https://qph.cf2.quoracdn.net/main-qimg-87b3952626997dee26ea570dfd37b482.webp", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://www.themoviedb.org/t/p/original/rT5TuOrhzi5QflTavW0fBtlw4Fz.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://i.pinimg.com/originals/38/08/4c/38084cd8dadf9d00224fa88cf4b5e377.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://vignette.wikia.nocookie.net/cinemorgue/images/9/93/Peaky-blinders-5277bd833cfd7.jpg/revision/latest?cb=20170311234242", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://i.pinimg.com/236x/46/30/ee/4630ee4602acb452c06b12adc8ad69e3.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://tse3.mm.bing.net/th?id=OIP.1YwHPpmg0KjBKctRly11VgAAAA&pid=Api&P=0&h=180", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
                )
            ),
            HomeList(
                homeListingType = HomeList.HomeListType.TRENDING_IN_MUSIC.name,
                title = HomeList.HomeListType.TRENDING_IN_MUSIC.type,
                mediaItems = arrayListOf(
                    MediaItem("https://i.etsystatic.com/20908186/r/il/7095d2/2924167517/il_570xN.2924167517_9xm3.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://www.aceshowbiz.com/images/wennpic/ellie-goulding-performs-04.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://posterwa.com/cdn/shop/products/SELENA2.jpg?v=1682618746&width=416", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://i.ebayimg.com/images/g/ljsAAOSwEzxYWIsd/s-l1200.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                    MediaItem("https://s.ecrater.com/stores/408554/595d0734a739b_408554b.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
                )
            )
        )
        _homeList.value = list
    }
}