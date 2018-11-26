package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.FavoriteFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.MatchFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setMatchFragment(savedInstanceState)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_match -> setMatchFragment(savedInstanceState)
                R.id.navigation_team -> setTeamFragment(savedInstanceState)
                R.id.navigation_favorite -> setFavoriteFragment(savedInstanceState)
            }
            true
        }
    }


    private fun setMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, MatchFragment.newInstance(1, MatchFragment.FRAGMENT_NAME))
                    .commit()
        }
    }

    private fun setTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, TeamFragment.newInstance(2, TeamFragment.FRAGMENT_NAME))
                    .commit()
        }
    }

    private fun setFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, FavoriteFragment
                            .newInstance(3, FavoriteFragment.FRAGMENT_NAME))
                    .commit()
        }
    }
}
