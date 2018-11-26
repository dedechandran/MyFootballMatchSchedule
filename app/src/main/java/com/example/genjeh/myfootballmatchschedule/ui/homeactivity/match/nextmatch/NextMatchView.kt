package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.nextmatch

import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface NextMatchView : MvpView {
    fun onShowLoading()
    fun onHideLoading()
    fun onLoadNextMatch(nextMatch : List<MatchEvent>)
    fun openDetailActivity(idEvent : String?,idHomeTeam : String?,idAwayTeam : String?)
    fun onShowToast(message : String)
    fun onLoadLeague(league : List<League>)
}