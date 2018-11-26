package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoritematch

import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteMatch
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface FavoriteMatchView : MvpView {
    fun onShowLoading()
    fun onHideLoading()
    fun onShowNoFavoriteMatch()
    fun onHideNoFavoriteMatch()
    fun openDetailActivity(idEvent : String?,idHomeTeam : String?,idAwayTeam : String?)
    fun onLoadFavoriteMatch(favMatch : List<FavoriteMatch>)
}