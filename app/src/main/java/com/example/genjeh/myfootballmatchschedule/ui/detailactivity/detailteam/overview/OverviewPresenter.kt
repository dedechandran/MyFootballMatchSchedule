package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.overview

import com.example.genjeh.myfootballmatchschedule.presenter.Presenter

class OverviewPresenter : Presenter<OverviewTabView> {
    private lateinit var overviewTabView: OverviewTabView
    override fun onAttach(view: OverviewTabView) {
        overviewTabView = view
    }

    fun showOverview(overview : String?){
        overviewTabView.onShowOverview(overview)
    }
}