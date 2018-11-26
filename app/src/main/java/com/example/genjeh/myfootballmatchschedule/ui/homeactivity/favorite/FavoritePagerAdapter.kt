package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoritematch.FavoriteMatchFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoriteteam.FavoriteTeamFragment

class FavoritePagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? = when(position){
        0 -> FavoriteMatchFragment.newInstance(position,FavoriteMatchFragment.FRAGMENT_NAME)
        1 -> FavoriteTeamFragment.newInstance(position,FavoriteTeamFragment.FRAGMENT_NAME)
        else -> {
            null
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int) = when(position){
        0 -> FavoriteMatchFragment.FRAGMENT_NAME
        1 -> FavoriteTeamFragment.FRAGMENT_NAME
        else -> {
            super.getPageTitle(position)
        }
    }
}