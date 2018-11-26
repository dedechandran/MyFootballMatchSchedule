package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.overview.OverviewFragment
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.player.PlayerFragment

class DetailTeamPagerAdapter(private val overview : String? , private val idTeam : String? ,fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> overview?.let { OverviewFragment.newInstance(position, it,OverviewFragment.FRAGMENT_NAME) }
        1 -> idTeam?.toInt()?.let { PlayerFragment.newInstance(position, it,PlayerFragment.FRAGMENT_NAME) }
        else -> {
            null
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int)= when(position){
        0 -> OverviewFragment.FRAGMENT_NAME
        1 -> PlayerFragment.FRAGMENT_NAME
        else -> {
            super.getPageTitle(position)
        }
    }
}