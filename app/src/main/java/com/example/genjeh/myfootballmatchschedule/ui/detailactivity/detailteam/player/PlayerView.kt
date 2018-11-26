package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.player

import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface PlayerView : MvpView {
    fun onShowToast(message : String)
    fun onLoadPlayer(data : List<Player>)
    fun onShowLoading()
    fun onHideLoading()
}