package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import kotlinx.android.synthetic.main.activity_detail_player.*


class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {
    override fun onLoadDetailPlayer(player: Player) {
        supportActionBar?.title = player.namePlayer
        Glide.with(this).load(player.imgPlayer).into(bannerPlayerTeam)
        if(player.weightPlayer == null){
            weightPlayer.text = "-"
        }else{
            weightPlayer.text = player.weightPlayer
        }

        if(player.heightPlayer == null){
            heightPlayer.text = "-"
        }else{
            heightPlayer.text = player.heightPlayer
        }

        if(player.positionPlayer == null){
            positionPlayer.text = "-"
        }else{
            positionPlayer.text = player.positionPlayer
        }

        if(player.descriptionPlayer == null){
            descPlayer.text = "-"
        }else{
            descPlayer.text = player.descriptionPlayer
        }

    }

    override fun getDataIntent() = intent.getParcelableExtra(ARG_PLAYER) as Player

    companion object {
        const val ARG_PLAYER = "player"
    }

    private lateinit var presenter: DetailPlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        setSupportActionBar(detailPlayerToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailPlayerPresenter()
        presenter.onAttach(this)
        presenter.loadDetailPlayer()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
