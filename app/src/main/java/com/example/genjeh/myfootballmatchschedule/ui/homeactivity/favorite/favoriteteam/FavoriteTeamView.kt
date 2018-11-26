package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoriteteam

import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteTeam
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface FavoriteTeamView : MvpView {
    fun onShowLoading()
    fun onHideLoading()
    fun onShowNoFavoriteTeam()
    fun onHideNoFavoriteTeam()
    fun openDetailActivity(team : Team?)
    fun onLoadFavoriteTeam(favTeam : List<FavoriteTeam>)
}