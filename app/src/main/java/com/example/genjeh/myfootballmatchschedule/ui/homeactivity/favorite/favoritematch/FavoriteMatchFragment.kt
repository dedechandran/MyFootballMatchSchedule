package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoritematch


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteMatch

import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailmatch.DetailMatchActivity
import com.example.genjeh.myfootballmatchschedule.ui.adapter.matchadapter.FavoriteMatchAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment(), FavoriteMatchView {
    override fun onShowNoFavoriteMatch() {
        noFavoriteMatch.visibility = View.VISIBLE
    }

    override fun onHideNoFavoriteMatch() {
        noFavoriteMatch.visibility = View.INVISIBLE
    }

    override fun onShowLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onResume() {
        presenter.loadFavoriteMatch()
        super.onResume()
    }

    override fun openDetailActivity(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        val intent = Intent(activity, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.ARG_MATCH_EVENT_ID, idEvent)
        intent.putExtra(DetailMatchActivity.ARG_HOME_TEAM_ID, idHomeTeam)
        intent.putExtra(DetailMatchActivity.ARG_AWAY_TEAM_ID, idAwayTeam)
        startActivity(intent)
    }

    override fun onLoadFavoriteMatch(favMatch: List<FavoriteMatch>) {
        listFavoriteMatch.clear()
        listFavoriteMatch.addAll(favMatch)
        adapter.notifyDataSetChanged()
    }


    companion object {
        const val FRAGMENT_NAME = "Match"
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
        fun newInstance(sectionNumber: Int, fragmentName: String): FavoriteMatchFragment {
            val fragment = FavoriteMatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            args.putString(ARG_FRAGMENT_NAME, fragmentName)
            fragment.arguments = args
            return fragment
        }

    }

    private val listFavoriteMatch: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var noFavoriteMatch: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_match, container, false)
        noFavoriteMatch = view.noFavoriteMatch
        swipeRefresh = view.swipeRefreshFavoriteMatch
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)

        adapter = FavoriteMatchAdapter(context, listFavoriteMatch) {
            presenter.moveDetailActivity(it.idEvent, it.idHomeTeam, it.idAwayTeam)
        }


        view.listFavoriteMatch.layoutManager = LinearLayoutManager(context)
        view.listFavoriteMatch.adapter = adapter

        presenter = FavoriteMatchPresenter(context)
        presenter.onAttach(this)
        presenter.loadFavoriteMatch()

        swipeRefresh.setOnRefreshListener {
            presenter.loadFavoriteMatch()
        }
        return view
    }


}
