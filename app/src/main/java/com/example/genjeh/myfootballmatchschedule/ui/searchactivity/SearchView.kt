package com.example.genjeh.myfootballmatchschedule.ui.searchactivity

import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface SearchView : MvpView  {
    fun onSearchMatch(listMatch : List<MatchEvent>)
    fun onSearchTeam(listTeam : List<Team>)
    fun onShowLoading()
    fun onHideLoading()
    fun getDataIntent() : String?
    fun onMoveDetailTeamActivity(team : Team)
    fun onMoveDetailMatchActivity(idEvent : String? , idHomeTeam : String? , idAwayTeam : String?)
    fun onShowToast(message : String)
}