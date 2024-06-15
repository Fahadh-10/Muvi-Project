package com.example.muviproject.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muviproject.R
import com.example.muviproject.activity.VideoPlayerActivity
import com.example.muviproject.adapter.HomeListAdapter
import com.example.muviproject.viewmodel.HomeViewModel
import com.example.muviproject.databinding.FragmentHomeBinding
import com.example.muviproject.helper.NavKey
import com.example.muviproject.helper.showToast
import com.example.muviproject.model.MediaItem

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeListAdapter: HomeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
        setupObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    /**
     * This function is used to set up the adapter for displaying home list items and handles item click events.
     */
    private fun setupAdapter() {
        binding.homeRV.layoutManager = LinearLayoutManager(context)
        homeListAdapter = HomeListAdapter(ArrayList(), requireContext())
        binding.homeRV.adapter = homeListAdapter
        homeListAdapter.setOnClickListeners(object : HomeListAdapter.OnItemClickListeners {
            override fun bannerItemClickListener(bannerUrl: String, position: Int) {
                moveToVideoActivity(bannerUrl)
            }

            override fun mediaItemClickListener(mediaUrl: String, position: Int) {
                moveToVideoActivity(mediaUrl)
            }

            override fun categoryItemClickListener(category: String, position: Int) {
                mContext.showToast(getString(R.string.coming_soon))
            }
        })
    }

    /**
     * Sets up the ViewModel by initializing an instance of HomeViewModel.
     */
    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    /**
     * Sets up observers for observing changes in home list data and updating the adapter accordingly.
     */
    private fun setupObservers() {
        homeViewModel.homeList.observe(viewLifecycleOwner, Observer { homeList ->
            homeList?.let {
                homeListAdapter.updateData(it)
            }
        })
    }

    /**
     * Navigates to the VideoPlayerActivity with the provided video URL.
     * @param videoUrl The URL of the video to be played.
     */
    private fun moveToVideoActivity(videoUrl: String) {
        val intent = Intent(requireContext(), VideoPlayerActivity::class.java).apply {
            putExtra(NavKey.VIDEO_URL, videoUrl)
        }
        startActivity(intent)
    }
}