package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteMatch
import com.example.genjeh.myfootballmatchschedule.model.db.database
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RepositoryCallback
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchPresenter(private val context: Context,private val apiRepository: ApiRepository) : Presenter<DetailMatchView> {
    private lateinit var detailMatchView: DetailMatchView

    override fun onAttach(view: DetailMatchView) {
        detailMatchView = view
    }

    fun saveToFavoriteDb(data: MatchEvent?) {
        try {
            context.database.use {
                insert(FavoriteMatch.TABLE_NAME,
                        FavoriteMatch.ID_EVENT to data?.idEvent,
                        FavoriteMatch.DATE_EVENT to data?.dateEvent,
                        FavoriteMatch.TIME_EVENT to data?.timeEvent,
                        FavoriteMatch.ID_HOME_TEAM to data?.idHomeTeam,
                        FavoriteMatch.NAME_HOME_TEAM to data?.nameHomeTeam,
                        FavoriteMatch.SCORE_HOME_TEAM to data?.scoreHomeTeam,
                        FavoriteMatch.ID_AWAY_TEAM to data?.idAwayTeam,
                        FavoriteMatch.NAME_AWAY_TEAM to data?.nameAwayTeam,
                        FavoriteMatch.SCORE_AWAY_TEAM to data?.scoreAwayTeam)
                detailMatchView.setFavoriteIcon(true)
                detailMatchView.onShowSnackBar(context.resources.getString(R.string.added_to_favorite_db))
            }

        } catch (e: SQLiteConstraintException) {
            detailMatchView.onShowSnackBar(e.localizedMessage)
        }
    }

    fun removeFromFavoriteDb(idEvent: String) {
        try {
            context.database.use {
                delete(FavoriteMatch.TABLE_NAME, "(ID_EVENT = {id})", "id" to idEvent)
                detailMatchView.setFavoriteIcon(false)
                detailMatchView.onShowSnackBar(context.resources.getString(R.string.removed_to_favorite_db))
            }
        } catch (e: SQLiteConstraintException) {
            detailMatchView.onShowSnackBar(e.localizedMessage)
        }
    }

    fun checkFavoriteMatch(idEvent : String): Boolean {
        var isFavorite = false
        context.database.use {
            val result = select(FavoriteMatch.TABLE_NAME)
                    .whereArgs("(ID_EVENT = {id})", "id" to idEvent)
            val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
            isFavorite = !favoriteMatch.isEmpty()
            detailMatchView.setFavoriteIcon(isFavorite)
        }
        return isFavorite
    }

    fun setImage(teamId: Int?, code: Int?) {
        apiRepository.teamDetailRepository(teamId,object : RepositoryCallback<Team>{
            override fun onSuccess(data: List<Team>?) {
                detailMatchView.onShowBanner(data?.get(0)?.teamBadge,code)
            }

            override fun onError() {
                detailMatchView.onShowSnackBar(context.resources.getString(R.string.message_failure_request))
            }

        })
    }

    fun setEventDetail(idEvent: Int?) {
        apiRepository.matchDetailRepository(idEvent,object : RepositoryCallback<MatchEvent>{
            override fun onSuccess(data: List<MatchEvent>?) {
                detailMatchView.onShowDetail(data?.get(0))
            }
            override fun onError() {
                detailMatchView.onShowSnackBar(context.resources.getString(R.string.message_failure_request))
            }
        })
    }
}