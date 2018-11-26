package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite.favoriteteam


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
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteTeam
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.ui.adapter.teamadapter.FavoriteTeamAdapter
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {
    override fun onShowNoFavoriteTeam() {
        noFavoriteTeam.visibility = View.VISIBLE
    }

    override fun onHideNoFavoriteTeam() {
        noFavoriteTeam.visibility = View.INVISIBLE
    }

    override fun onShowLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun openDetailActivity(team: Team?) {
        val intent = Intent(activity, DetailTeamActivity::class.java)
        intent.putExtra(DetailTeamActivity.ARG_TEAM, team)
        startActivity(intent)
    }

    override fun onLoadFavoriteTeam(favTeam: List<FavoriteTeam>) {
        listFavoriteTeam.clear()
        listFavoriteTeam.addAll(favTeam)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        presenter.loadFavoriteTeam()
        super.onResume()
    }

    companion object {
        const val FRAGMENT_NAME = "Team"

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_FRAGMENT_NAME = "fragment_name"

        fun newInstance(sectionNumber: Int, fragmentName: String): FavoriteTeamFragment {
            val fragment = FavoriteTeamFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            args.putString(ARG_FRAGMENT_NAME, fragmentName)
            return fragment
        }
    }


    private val listFavoriteTeam: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var noFavoriteTeam: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        swipeRefresh = view.swipeRefreshFavoriteTeam
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)

        noFavoriteTeam = view.noFavoriteTeam

        adapter = FavoriteTeamAdapter(context, listFavoriteTeam) {
            presenter.moveDetailActivity(Team(it.idTeam, it.nameTeam, it.yearTeam, it.stadiumTeam, it.descTeam, it.teamBadge))
        }

        view.listFavoriteTeam.layoutManager = LinearLayoutManager(context)
        view.listFavoriteTeam.adapter = adapter

        presenter = FavoriteTeamPresenter(context)
        presenter.onAttach(this)
        presenter.loadFavoriteTeam()

        swipeRefresh.setOnRefreshListener {
            presenter.loadFavoriteTeam()
        }

        return view
    }


}
