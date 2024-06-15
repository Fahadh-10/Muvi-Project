package com.example.muviproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.muviproject.R
import com.example.muviproject.fragment.AccountFragment
import com.example.muviproject.fragment.HomeFragment
import com.example.muviproject.fragment.MenuFragment
import com.example.muviproject.fragment.SearchFragment
import com.example.muviproject.databinding.ActivityMainBinding
import com.example.muviproject.helper.showToast
import com.example.muviproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        setUpViewModelObserver()
        setUpBottomNV()
        setUpListeners()
    }

    /**
     * Sets up observers for observing changes and updating the UI accordingly.
     */
    private fun setUpViewModelObserver(){
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                showToast(it)
            }
        })
    }

    /**
     * Sets up click listeners for UI elements
     */
    private fun setUpListeners() {
        binding.notificationIconIV.setOnClickListener {
            viewModel.onNotificationIconClicked()
        }

        binding.tvIconIV.setOnClickListener {
            viewModel.onTvIconClicked()
        }
    }

    /**
     * Set up the bottom navigation using menuItemId
     */
    private fun setUpBottomNV() {
        binding.bottomNV.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> loadFragment(HomeFragment())
                R.id.searchFragment -> loadFragment(SearchFragment())
                R.id.accountFragment -> loadFragment(AccountFragment())
                R.id.menuFragment -> loadFragment(MenuFragment())
            }
            true
        }
    }

    /**
     * Load fragment
     * @param fragment
     */
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navigationFL, fragment)
        transaction.commit()
    }
}