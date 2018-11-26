package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RepositoryCallback
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter

class TeamPresenter(private val context: Context?, private val apiRepository: ApiRepository) : Presenter<TeamView> {
    private lateinit var teamView: TeamView
    override fun onAttach(view: TeamView) {
        teamView = view
    }

    fun loadTeam(leagueId: Int?) {
        teamView.onShowLoading()
        apiRepository.teamRepository(leagueId, object : RepositoryCallback<Team> {
            override fun onSuccess(data: List<Team>?) {
                teamView.onHideLoading()
                data?.let { teamView.onLoadTeam(it) }
            }
            override fun onError() {
                context?.resources?.getString(R.string.message_failure_request)?.let { teamView.onShowToast(it) }
            }

        })
    }

    fun loadLeague(){
        apiRepository.leagueRepository(object : RepositoryCallback<League>{
            override fun onSuccess(data: List<League>?) {
                data?.let { teamView.onLoadLeague(it) }
            }

            override fun onError() {
                context?.resources?.getString(R.string.message_failure_request)?.let { teamView.onShowToast(it) }
            }

        })
    }

    fun getLeagueName(league: List<League>): List<String> {
        val leagueName: MutableList<String> = mutableListOf()
        var leagueIndex = 0
        for (index in league.indices) {
            if (league[index].nameSport.equals("Soccer")) {
                league[index].nameLeague?.let { leagueName.add(leagueIndex, it) }
                leagueIndex++
            }
        }
        return leagueName
    }

    fun getLeagueId(league: List<League>, nameLeague : String) : Int? {
        var found = false
        var index = 0
        var id : Int? = null
        while (!found and (index<league.size)){
            if(nameLeague == league[index].nameLeague){
                id = league[index].idLeague?.toInt()
                found = true
            }
            index++
        }
        return id
    }


}