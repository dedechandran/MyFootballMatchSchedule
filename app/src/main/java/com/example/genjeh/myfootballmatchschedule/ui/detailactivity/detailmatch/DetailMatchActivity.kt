package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitInstance
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitService
import com.example.genjeh.myfootballmatchschedule.utils.Util
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.design.snackbar


class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    companion object {
        const val ARG_MATCH_EVENT_ID = "match_event_id"
        const val ARG_HOME_TEAM_ID = "home_team_id"
        const val ARG_AWAY_TEAM_ID = "away_team_id"
    }

    override fun onShowDetailMatch() {
        presenter.setEventDetail(getDataIntent()[0]?.toInt())
        presenter.setImage(getDataIntent()[1]?.toInt(), 1)
        presenter.setImage(getDataIntent()[2]?.toInt(), 2)
    }

    override fun onShowSnackBar(message: String) {
        snackbar(scroll_view, message).show()
    }

    override fun setFavoriteIcon(isFav: Boolean) {
        val favoriteOption = optionMenu?.getItem(0)
        if (isFav) favoriteOption?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_white_24dp) else favoriteOption?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_border_white_24dp)
    }

    override fun onShowBanner(url: String?, code: Int?) {
        when (code) {
            1 -> Glide.with(this).load(url).into(bannerHomeTeam)
            2 -> Glide.with(this).load(url).into(bannerAwayTeam)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        optionMenu = menu
        isFavorite = presenter.checkFavoriteMatch(getDataIntent()[0].toString())
        return super.onCreateOptionsMenu(menu)
    }


    override fun onShowDetail(data: MatchEvent?) {
        dataEvent = data
        detail_tanggal.text = Util.convertFormatDate(data?.dateEvent)
        detail_time.text = Util.getGmtTime(data?.timeEvent?.split("+")?.get(0)+"+00:00","7")
        if (data?.scoreHomeTeam != null) {
            scoreDetailHomeTeam.text = data.scoreHomeTeam
        } else {
            scoreDetailHomeTeam.text = "-"
        }

        if (data?.scoreAwayTeam != null) {
            scoreDetailAwayTeam.text = data.scoreAwayTeam
        } else {
            scoreDetailAwayTeam.text = "-"
        }

        val listGoalsHome = data?.goalsDetailHomeTeam?.split(";")
        setArrayData(listGoalsHome, goalsHomeTeam)

        val listGoalsAway = data?.goalsDetailAwayTeam?.split(";")
        setArrayData(listGoalsAway, goalsAwayTeam)

        if (data?.shootsHomeTeam != null) {
            shootsHomeTeam.text = data.shootsHomeTeam
        } else {
            shootsHomeTeam.text = "-"
        }

        if (data?.shootsAwayTeam != null) {
            shootsAwayTeam.text = data.shootsAwayTeam
        } else {
            shootsAwayTeam.text = "-"
        }

        val listYellowCardsHome = data?.yellowCardsHomeTeam?.split(";")
        setArrayData(listYellowCardsHome, yellowCardsHomeTeam)

        val listRedCardsHome = data?.redCardsHomeTeam?.split(";")
        setArrayData(listRedCardsHome, redCardsHomeTeam)

        val listYellowCardsAway = data?.yellowCardsAwayTeam?.split(";")
        setArrayData(listYellowCardsAway, yellowCardsAwayTeam)

        val listRedCardsAway = data?.redCardsAwayTeam?.split(";")
        setArrayData(listRedCardsAway, redCardsAwayTeam)

        val listGoalKeeperHome = data?.goalKeeperHomeTeam?.split("; ")
        setArrayData(listGoalKeeperHome, goalKeeperHomeTeam)

        val listDefenseHome = data?.defenseHomeTeam?.split("; ")
        setArrayData(listDefenseHome, defenseHomeTeam)

        val listMidfieldHome = data?.midfieldHomeTeam?.split("; ")
        setArrayData(listMidfieldHome, midFieldsHomeTeam)

        val listForwardHome = data?.forwardHomeTeam?.split("; ")
        setArrayData(listForwardHome, forwardHomeTeam)

        val listSubstituteHome = data?.substitutesHomeTeam?.split("; ")
        setArrayData(listSubstituteHome, subtituteHomeTeam)

        val listGoalKeeperAway = data?.goalKeeperAwayTeam?.split("; ")
        setArrayData(listGoalKeeperAway, goalKeeperAwayTeam)

        val listDefenseAway = data?.defenseAwayTeam?.split("; ")
        setArrayData(listDefenseAway, defenseAwayTeam)

        val listMidfieldAway = data?.midfieldAwayTeam?.split("; ")
        setArrayData(listMidfieldAway, midFieldAwayTeam)

        val listForwardAway = data?.forwardAwayTeam?.split("; ")
        setArrayData(listForwardAway, forwardAwayTeam)

        val listSubstituteAway = data?.substitutesAwayTeam?.split("; ")
        setArrayData(listSubstituteAway, subtituteAwayTeam)

    }

    override fun getDataIntent(): MutableList<String?> {
        val listId: MutableList<String?> = mutableListOf()
        listId.add(0, intent.getStringExtra(ARG_MATCH_EVENT_ID))
        listId.add(1, intent.getStringExtra(ARG_HOME_TEAM_ID))
        listId.add(2, intent.getStringExtra(ARG_AWAY_TEAM_ID))
        return listId
    }

    private fun setArrayData(arr: List<String>?, tv: TextView) {
        if (arr != null && arr[0] != "") {
            for (i in arr.indices) {
                val value: String? = if (i == arr.size - 1) {
                    arr[i]
                } else {
                    arr[i] + "\n"
                }
                tv.append(value)
            }
        } else {
            tv.text = "-"
        }
    }

    private lateinit var presenter: DetailMatchPresenter
    private var optionMenu: Menu? = null
    private var dataEvent: MatchEvent? = null
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.detail_activity)

        val service = RetrofitInstance.newInstance()?.create(RetrofitService::class.java)
        val apiRepo = ApiRepository(service)

        presenter = DetailMatchPresenter(applicationContext, apiRepo)
        presenter.onAttach(this)
        onShowDetailMatch()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.favorite_option_menu ->
                isFavorite = if (!isFavorite) {
                    presenter.saveToFavoriteDb(dataEvent)
                    true
                } else {
                    presenter.removeFromFavoriteDb(dataEvent?.idEvent.toString())
                    false
                }

        }
        return super.onOptionsItemSelected(item)
    }

}
