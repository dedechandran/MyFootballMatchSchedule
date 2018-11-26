package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch

import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface DetailMatchView : MvpView {
    fun onShowBanner(url : String?,code : Int?)
    fun onShowDetail(data : MatchEvent?)
    fun getDataIntent() : MutableList<String?>
    fun setFavoriteIcon(isFav : Boolean)
    fun onShowSnackBar(message : String)
    fun onShowDetailMatch()
}