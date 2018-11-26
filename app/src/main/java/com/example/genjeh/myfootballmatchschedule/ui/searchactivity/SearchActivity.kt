package com.example.genjeh.myfootballmatchschedule.ui.searchactivity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitInstance
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitService
import com.example.genjeh.myfootballmatchschedule.ui.adapter.matchadapter.MatchEventAdapter
import com.example.genjeh.myfootballmatchschedule.ui.adapter.teamadapter.TeamAdapter
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch.DetailMatchActivity
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.DetailTeamActivity
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.MatchFragment
import com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team.TeamFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchView {
    override fun onShowToast(message: String) {
        ctx.toast(message)
    }

    override fun onMoveDetailTeamActivity(team: Team) {
        val intent = Intent(ctx, DetailTeamActivity::class.java)
        intent.putExtra(DetailTeamActivity.ARG_TEAM, team)
        startActivity(intent)
    }

    override fun onMoveDetailMatchActivity(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        val intent = Intent(ctx, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.ARG_MATCH_EVENT_ID, idEvent)
        intent.putExtra(DetailMatchActivity.ARG_HOME_TEAM_ID, idHomeTeam)
        intent.putExtra(DetailMatchActivity.ARG_AWAY_TEAM_ID, idAwayTeam)
        startActivity(intent)
    }

    companion object {
        const val ARG_ACTIVE_FRAGMENT = "active_fragment"
    }


    override fun getDataIntent() = intent.getStringExtra(ARG_ACTIVE_FRAGMENT) as String

    override fun onSearchMatch(listMatch: List<MatchEvent>) {
        listResultMatch.clear()
        listResultMatch.addAll(listMatch)
        matchEventAdapter.notifyDataSetChanged()
    }

    override fun onSearchTeam(listTeam: List<Team>) {
        listResultTeam.clear()
        listResultTeam.addAll(listTeam)
        teamAdapter.notifyDataSetChanged()
    }

    override fun onShowLoading() {
        progressSearch.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressSearch.visibility = View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        materialSearch.showSearch()
        materialSearch.setHint("Search $fragmentName")
        materialSearch.setHintTextColor(Color.GRAY)
        materialSearch.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.search(fragmentName, query)
                materialSearch.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.search(fragmentName, newText)
                return true
            }
        })
        materialSearch.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                finish()
            }

            override fun onSearchViewShown() {
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private val listResultMatch: MutableList<MatchEvent> = mutableListOf()
    private val listResultTeam: MutableList<Team> = mutableListOf()
    private lateinit var presenter: SearchPresenter
    private lateinit var matchEventAdapter: MatchEventAdapter
    private lateinit var teamAdapter: TeamAdapter
    private var fragmentName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(searchToolbar)
        fragmentName = getDataIntent()
        supportActionBar?.title = fragmentName
        listSearchResult.layoutManager = LinearLayoutManager(ctx)

        if (fragmentName.equals(MatchFragment.FRAGMENT_NAME)) {
            matchEventAdapter = MatchEventAdapter(ctx, listResultMatch) {
                presenter.moveDetailMatch(it.idEvent, it.idHomeTeam, it.idAwayTeam)
            }
            listSearchResult.adapter = matchEventAdapter
        } else if (fragmentName.equals(TeamFragment.FRAGMENT_NAME)) {
            teamAdapter = TeamAdapter(ctx, listResultTeam) {
                presenter.moveDetailTeam(it)
            }
            listSearchResult.adapter = teamAdapter
        }

        val service = RetrofitInstance.newInstance()?.create(RetrofitService::class.java)
        val apiRepo = ApiRepository(service)
        presenter = SearchPresenter(ctx,apiRepo)
        presenter.onAttach(this)

    }


}
