package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.lastmatch

import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface LastMatchView : MvpView{
    fun onShowLoading()
    fun onHideLoading()
    fun onLoadLastMatch(lastMatch : List<MatchEvent>)
    fun openDetailActivity(idEvent : String?,idHomeTeam : String?,idAwayTeam : String?)
    fun onShowToast(message : String)
    fun onLoadLeague(league : List<League>)
}