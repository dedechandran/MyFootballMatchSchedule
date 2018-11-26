package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoritematch

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteMatch
import com.example.genjeh.myfootballmatchschedule.model.db.database
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter(val context : Context?) : Presenter<FavoriteMatchView> {
    private lateinit var favoriteMatchView: FavoriteMatchView

    override fun onAttach(view: FavoriteMatchView) {
        favoriteMatchView = view
    }

    fun moveDetailActivity(eventId : String?,idHomeTeam : String?,idAwayTeam : String?){
        favoriteMatchView.openDetailActivity(eventId,idHomeTeam,idAwayTeam)
    }

    fun loadFavoriteMatch(){
        favoriteMatchView.onShowLoading()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_NAME)
            val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
            favoriteMatchView.onLoadFavoriteMatch(favoriteMatch)
            if(favoriteMatch.isEmpty()){
                favoriteMatchView.onShowNoFavoriteMatch()
            }else{
                favoriteMatchView.onHideNoFavoriteMatch()
            }
            favoriteMatchView.onHideLoading()
        }
    }
}