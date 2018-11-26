package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.nextmatch

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RepositoryCallback
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter

class NextMatchPresenter(private val context : Context?, private val apiRepository: ApiRepository) : Presenter<NextMatchView> {
    private lateinit var nextMatchView: NextMatchView

    override fun onAttach(view: NextMatchView) {
        nextMatchView = view
    }

    fun loadNextMatch(leagueId : Int?){
        nextMatchView.onShowLoading()
        apiRepository.nextMatchRepository(leagueId,object : RepositoryCallback<MatchEvent>{
            override fun onSuccess(data: List<MatchEvent>?) {
                nextMatchView.onHideLoading()
                data?.let { nextMatchView.onLoadNextMatch(it) }
            }

            override fun onError() {
                context?.resources?.getString(R.string.message_failure_request)?.let { nextMatchView.onShowToast(it) }
            }

        })
    }

    fun loadLeague(){
        apiRepository.leagueRepository(object : RepositoryCallback<League>{
            override fun onSuccess(data: List<League>?) {
                data?.let { nextMatchView.onLoadLeague(it) }
            }

            override fun onError() {
                context?.resources?.getString(R.string.message_failure_request)?.let { nextMatchView.onShowToast(it) }
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

    fun moveDetailActivity(eventId : String?,idHomeTeam : String?,idAwayTeam : String?){
        nextMatchView.openDetailActivity(eventId,idHomeTeam,idAwayTeam)
    }

}