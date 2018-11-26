package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team

import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface TeamView : MvpView {
    fun onShowLoading()
    fun onHideLoading()
    fun onLoadTeam(team : List<Team>)
    fun onShowToast(message : String)
    fun onLoadLeague(league : List<League>)
}