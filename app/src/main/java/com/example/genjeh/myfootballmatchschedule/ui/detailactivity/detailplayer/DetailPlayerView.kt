package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailplayer

import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface DetailPlayerView : MvpView {
    fun onLoadDetailPlayer(player : Player)
    fun getDataIntent() : Player?
}