package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.overview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.genjeh.myfootballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment(), OverviewTabView {
    companion object {
        const val FRAGMENT_NAME = "Overview"

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_OVERVIEW_TEAM = "overview_team"
        private const val ARG_FRAGMENT_NAME = "fragment_name"


        fun newInstance(sectionNumber : Int ,overviewTeam : String,fragmentName : String) : OverviewFragment{
            val fragment = OverviewFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER,sectionNumber)
            args.putString(ARG_OVERVIEW_TEAM,overviewTeam)
            args.putString(ARG_FRAGMENT_NAME,fragmentName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onShowOverview(overview: String?) {
        tvOverview.text = overview
    }

    private lateinit var tvOverview : TextView
    private lateinit var presenter : OverviewPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        tvOverview = view.tv_overview

        presenter = OverviewPresenter()
        presenter.onAttach(this)
        presenter.showOverview(arguments?.getString(ARG_OVERVIEW_TEAM))

        return view
    }

}
