package com.softaai.agrostardemoassignment.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.softaai.agrostardemoassignment.R
import com.softaai.agrostardemoassignment.adapter.ViewPagerAdapter
import com.softaai.agrostardemoassignment.utils.LocationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val locationUtils = LocationUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                collapsing_toolbar.title = " "
                isShow = true
            } else if (isShow) {
                collapsing_toolbar.title = getString(R.string.collapsing_toolbar_title)
                isShow = false
            }
        })

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UsersFragment(), "Users")
        adapter.addFragment(FriendsFragment(), "Friends")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onStart() {
        super.onStart()
        locationUtils.checkLocationPermission(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (locationUtils.checkLocationPermissionsResult(requestCode, permissions, grantResults)) {
            Toast.makeText(
                this, "Location permission granted to this app",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                this, "Please grant permission to this app",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}