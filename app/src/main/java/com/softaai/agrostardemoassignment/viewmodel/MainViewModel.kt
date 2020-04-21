package com.softaai.agrostardemoassignment.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.softaai.agrostardemoassignment.adapter.ViewPagerAdapter
import com.softaai.agrostardemoassignment.view.FriendsFragment
import com.softaai.agrostardemoassignment.view.UsersFragment

class MainViewModel internal constructor(context: FragmentActivity) : BaseObservable() {
    var mContext: FragmentActivity
    var adapter: ViewPagerAdapter? = null

    @get:Bindable
    val pagerAdapter: PagerAdapter?
        get() = adapter

    private fun createViewPager() {
        val adapter = ViewPagerAdapter(mContext.supportFragmentManager)
        adapter.addFragment(UsersFragment(), "Users")
        adapter.addFragment(FriendsFragment(), "Friends")
    }

    init {
        mContext = context
        createViewPager()
    }
}