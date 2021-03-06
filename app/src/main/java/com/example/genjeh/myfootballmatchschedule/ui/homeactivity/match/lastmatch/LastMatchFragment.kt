package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match.lastmatch


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitInstance
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitService
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch.DetailMatchActivity
import com.example.genjeh.myfootballmatchschedule.ui.adapter.matchadapter.MatchEventAdapter
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), LastMatchView {
    override fun onLoadLeague(league: List<League>) {
        listLeague.addAll(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, presenter.getLeagueName(league))
        spinner.adapter = spinnerAdapter
        idLeague = league[0].idLeague?.toInt()
        presenter.loadLastMatch(idLeague)
    }

    override fun onShowToast(message: String) {
        context?.toast(message)?.show()
    }

    override fun openDetailActivity(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        val intent = Intent(activity, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.ARG_MATCH_EVENT_ID, idEvent)
        intent.putExtra(DetailMatchActivity.ARG_HOME_TEAM_ID, idHomeTeam)
        intent.putExtra(DetailMatchActivity.ARG_AWAY_TEAM_ID, idAwayTeam)

        startActivity(intent)
    }

    override fun onShowLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onLoadLastMatch(lastMatch: List<MatchEvent>) {
        listLastMatch.clear()
        listLastMatch.addAll(lastMatch)
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val FRAGMENT_NAME = "Last Match"
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_FRAGMENT_NAME = "fragment_name"
        private const val ARG_SECTION_NUMBER = "section_number"


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int, fragmentName: String): LastMatchFragment {
            val fragment = LastMatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            args.putString(ARG_FRAGMENT_NAME, fragmentName)
            fragment.arguments = args
            return fragment
        }
    }


    private val listLastMatch: MutableList<MatchEvent> = mutableListOf()
    private val listLeague: MutableList<League> = mutableListOf()
    private lateinit var adapter: MatchEventAdapter
    private lateinit var presenter: LastMatchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var idLeague: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_last_match, container, false)


        swipeRefresh = view.swipeRefreshLastMatch
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)

        spinner = view.leagueLastMatchSpinner

        adapter = MatchEventAdapter(context, listLastMatch) {
            presenter.moveDetailActivity(it.idEvent, it.idHomeTeam, it.idAwayTeam)
        }
        view.listLastMatch.layoutManager = LinearLayoutManager(context)
        view.listLastMatch.adapter = adapter

        val service = RetrofitInstance.newInstance()?.create(RetrofitService::class.java)
        val apiRepo = ApiRepository(service)
        presenter = LastMatchPresenter(activity?.applicationContext, apiRepo)
        presenter.onAttach(this)
        presenter.loadLeague()

        swipeRefresh.setOnRefreshListener {
            presenter.loadLastMatch(idLeague)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = presenter.getLeagueId(listLeague, leagueLastMatchSpinner.selectedItem.toString())
                presenter.loadLastMatch(idLeague)
            }

        }

        return view
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }


}
