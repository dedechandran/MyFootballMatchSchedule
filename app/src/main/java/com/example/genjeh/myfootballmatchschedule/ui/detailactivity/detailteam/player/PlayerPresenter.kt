package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.player

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RepositoryCallback
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter

class PlayerPresenter(private val context: Context?, private val apiRepository: ApiRepository) : Presenter<PlayerView> {
    private lateinit var playerView: PlayerView
    override fun onAttach(view: PlayerView) {
        playerView = view
    }

    fun loadPlayer(teamId: Int?) {
        playerView.onShowLoading()
        apiRepository.playerRepository(teamId, object : RepositoryCallback<Player> {
            override fun onSuccess(data: List<Player>?) {
                playerView.onHideLoading()
                data?.let { playerView.onLoadPlayer(it) }
            }

            override fun onError() {
                playerView.onHideLoading()
                context?.resources?.getString(R.string.message_failure_request)?.let { playerView.onShowToast(it) }
            }

        })
    }
}