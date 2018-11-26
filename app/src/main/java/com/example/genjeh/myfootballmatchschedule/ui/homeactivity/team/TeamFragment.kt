package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.team


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitInstance
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitService
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.DetailTeamActivity
import com.example.genjeh.myfootballmatchschedule.ui.adapter.teamadapter.TeamAdapter
import com.example.genjeh.myfootballmatchschedule.ui.searchactivity.SearchActivity
import kotlinx.android.synthetic.main.fragment_team.view.*
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(), TeamView {
    override fun onShowLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onLoadTeam(team: List<Team>) {
        listTeam.clear()
        listTeam.addAll(team)
        adapter.notifyDataSetChanged()
    }

    override fun onShowToast(message: String) {
        context?.toast(message)
    }

    override fun onLoadLeague(league: List<League>) {
        listLeague.addAll(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, presenter.getLeagueName(league))
        spinner.adapter = spinnerAdapter
        idLeague = league[0].idLeague?.toInt()
        presenter.loadTeam(idLeague)
    }

    companion object {
        const val FRAGMENT_NAME = "Team"

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_FRAGMENT_NAME = "fragment_name"

        fun newInstance(sectionNumber: Int, fragmentName: String): TeamFragment {
            val fragment = TeamFragment()
            val args = Bundle()
            args.putString(ARG_FRAGMENT_NAME, fragmentName)
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }


    private val listTeam: MutableList<Team> = mutableListOf()
    private val listLeague: MutableList<League> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var idLeague: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(view.teamToolbar)
        activity.supportActionBar?.title = context?.resources?.getString(R.string.team)
        setHasOptionsMenu(true)

        swipeRefresh = view.swipeRefreshTeam
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)

        spinner = view.leagueTeamSpinner

        adapter = TeamAdapter(context, listTeam) {
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra(DetailTeamActivity.ARG_TEAM, it)
            startActivity(intent)
        }

        view.listTeam.layoutManager = LinearLayoutManager(context)
        view.listTeam.adapter = adapter

        val service = RetrofitInstance.newInstance()?.create(RetrofitService::class.java)
        val apiRepo = ApiRepository(service)

        presenter = TeamPresenter(context, apiRepo)
        presenter.onAttach(this)
        presenter.loadLeague()

        swipeRefresh.setOnRefreshListener {
            presenter.loadTeam(idLeague)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = presenter.getLeagueId(listLeague, spinner.selectedItem.toString())
                presenter.loadTeam(idLeague)
            }

        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.search_menu) {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra(SearchActivity.ARG_ACTIVE_FRAGMENT, FRAGMENT_NAME)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}
