package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailplayer

import com.example.genjeh.myfootballmatchschedule.presenter.Presenter

class DetailPlayerPresenter : Presenter<DetailPlayerView> {
    private lateinit var detailPlayerView: DetailPlayerView
    override fun onAttach(view: DetailPlayerView) {
        detailPlayerView = view
    }

    fun loadDetailPlayer() {
        val player = detailPlayerView.getDataIntent()
        player?.let { detailPlayerView.onLoadDetailPlayer(it) }
    }
}