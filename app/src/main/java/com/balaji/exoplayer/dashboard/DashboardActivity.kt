package com.balaji.exoplayer.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.balaji.exoplayer.R
import com.balaji.exoplayer.base.BaseActivity
import com.balaji.exoplayer.databinding.ActivityDashboardBinding
import com.balaji.exoplayer.folders.FoldersFragment
import com.balaji.exoplayer.videos.VideosFragment

class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.videos -> {
                    replaceFragment(VideosFragment())
                    true
                }
                R.id.folder -> {
                    replaceFragment(FoldersFragment())
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.videos
    }

    override fun getCurrentFragment(): Fragment? {
        val manager: FragmentManager = supportFragmentManager
        return manager.findFragmentById(R.id.fragmentContainer)
    }
}