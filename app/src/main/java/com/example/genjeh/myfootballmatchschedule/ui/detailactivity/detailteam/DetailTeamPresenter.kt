package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam


import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteTeam
import com.example.genjeh.myfootballmatchschedule.model.db.database
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailTeamPresenter(private val context: Context) : Presenter<DetailTeamView> {
    private lateinit var detailTeamView: DetailTeamView
    override fun onAttach(view: DetailTeamView) {
        detailTeamView = view
    }

    fun showTeamDetail(data: Team?) {
        detailTeamView.onShowDetail(data)
    }


    fun saveToFavoriteDb(data: Team?) {
        try {
            context.database.use {
                insert(FavoriteTeam.TABLE_NAME,
                        FavoriteTeam.ID_TEAM to data?.idTeam,
                        FavoriteTeam.NAME_TEAM to data?.nameTeam,
                        FavoriteTeam.TEAM_BADGE to data?.teamBadge,
                        FavoriteTeam.YEAR_TEAM to data?.formedYearTeam,
                        FavoriteTeam.STADIUM_TEAM to data?.stadiumTeam,
                        FavoriteTeam.DESC_TEAM to data?.descriptionTeam)
                detailTeamView.setFavoriteIcon(true)
                detailTeamView.onShowSnackBar(context.resources.getString(R.string.added_to_favorite_db))
            }
        } catch (e: SQLiteConstraintException) {
            detailTeamView.onShowSnackBar(e.localizedMessage)
        }

    }

    fun removeFromFavoriteDb(idTeam: String) {
        try {
            context.database.use {
                delete(FavoriteTeam.TABLE_NAME, "(ID_TEAM = {id})", "id" to idTeam)
                detailTeamView.setFavoriteIcon(false)
                detailTeamView.onShowSnackBar(context.resources.getString(R.string.removed_to_favorite_db))
            }
        } catch (e: SQLiteConstraintException) {
            detailTeamView.onShowSnackBar(e.localizedMessage)
        }
    }

    fun checkFavoriteMatch(idTeam: String): Boolean {
        var isFavorite = false
        context.database.use {
            val result = select(FavoriteTeam.TABLE_NAME)
                    .whereArgs("(ID_TEAM = {id})", "id" to idTeam)
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
            isFavorite = !favoriteTeam.isEmpty()
            detailTeamView.setFavoriteIcon(isFavorite)
        }

        return isFavorite
    }
}