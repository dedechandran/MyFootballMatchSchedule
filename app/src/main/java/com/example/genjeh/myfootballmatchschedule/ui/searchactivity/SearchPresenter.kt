package com.example.genjeh.myfootballmatchschedule.ui.searchactivity

import android.content.Context
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RepositoryCallback
import com.example.genjeh.myfootballmatchschedule.presenter.Presenter
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.MatchFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team.TeamFragment

class SearchPresenter(private val context: Context, private val apiRepository: ApiRepository) : Presenter<SearchView> {
    private lateinit var searchView: SearchView
    override fun onAttach(view: SearchView) {
        searchView = view
    }

    fun search(fragmentName: String?, query: String?) {
        searchView.onShowLoading()
        when (fragmentName) {
            MatchFragment.FRAGMENT_NAME ->
                apiRepository.searchEvent(query, object : RepositoryCallback<MatchEvent> {
                    override fun onSuccess(data: List<MatchEvent>?) {
                        searchView.onHideLoading()
                        data?.let { searchView.onSearchMatch(it) }
                    }

                    override fun onError() {
                        searchView.onHideLoading()
                        context.resources?.getString(R.string.message_failure_request)?.let { searchView.onShowToast(it) }
                    }
                })
            TeamFragment.FRAGMENT_NAME ->
                apiRepository.searchTeam(query, object : RepositoryCallback<Team> {
                    override fun onSuccess(data: List<Team>?) {
                        searchView.onHideLoading()
                        data?.let { searchView.onSearchTeam(it) }
                    }

                    override fun onError() {
                        searchView.onHideLoading()
                        context.resources?.getString(R.string.message_failure_request)?.let { searchView.onShowToast(it) }
                    }
                })
        }
    }

    fun moveDetailMatch(idEvent : String?, idHomeTeam : String?, idAwayTeam : String?){
        searchView.onMoveDetailMatchActivity(idEvent,idHomeTeam,idAwayTeam)
    }

    fun moveDetailTeam(team : Team){
        searchView.onMoveDetailTeamActivity(team)
    }


}