package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoriteteam

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteTeam
import com.example.genjeh.myfootballmatchschedule.model.db.database
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val context : Context?) : Presenter<FavoriteTeamView> {
    private lateinit var favoriteTeamView: FavoriteTeamView
    override fun onAttach(view: FavoriteTeamView) {
       favoriteTeamView = view
    }

    fun moveDetailActivity(team : Team?){
        favoriteTeamView.openDetailActivity(team)
    }

    fun loadFavoriteTeam(){
        favoriteTeamView.onShowLoading()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_NAME)
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
            favoriteTeamView.onLoadFavoriteTeam(favoriteTeam)
            if(favoriteTeam.isEmpty()){
                favoriteTeamView.onShowNoFavoriteTeam()
            }else{
                favoriteTeamView.onHideNoFavoriteTeam()
            }
            favoriteTeamView.onHideLoading()
        }
    }
}