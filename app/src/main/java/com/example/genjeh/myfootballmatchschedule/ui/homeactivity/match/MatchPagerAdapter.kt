package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.lastmatch.LastMatchFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.nextmatch.NextMatchFragment


class MatchPagerAdapter (fm : FragmentManager?): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? = when(position){
        0 -> LastMatchFragment.newInstance(position,LastMatchFragment.FRAGMENT_NAME)
        1 -> NextMatchFragment.newInstance(position,LastMatchFragment.FRAGMENT_NAME)
        else -> {
            null
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int)= when(position){
        0 -> LastMatchFragment.FRAGMENT_NAME
        1 -> NextMatchFragment.FRAGMENT_NAME
        else -> {
            super.getPageTitle(position)
        }
    }
}