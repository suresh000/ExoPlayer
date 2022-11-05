package com.balaji.exoplayer.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.balaji.exoplayer.R
import com.balaji.exoplayer.base.BaseActivity
import com.balaji.exoplayer.databinding.ActivityDashboardBinding
import com.balaji.exoplayer.folders.FoldersFragment
import com.balaji.exoplayer.videos.VideosFragment

class DashboardActivity : BaseActivity() {

    private val PERMISSION_REQUEST_CODE by lazy { 11 }

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestRuntimePermission()

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

    private fun requestRuntimePermission() : Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "Storage permission not granted", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun getCurrentFragment(): Fragment? {
        val manager: FragmentManager = supportFragmentManager
        return manager.findFragmentById(R.id.fragmentContainer)
    }
}