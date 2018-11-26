package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam

import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface DetailTeamView : MvpView {
    fun onShowDetail(data : Team?)
    fun setFavoriteIcon(isFav : Boolean)
    fun onShowSnackBar(message : String)
    fun getDataIntent() : Team?
}