package com.example.genjeh.myfootballmatchschedule.presenter

import com.example.genjeh.myfootballmatchschedule.ui.MvpView

interface Presenter<T : MvpView> {
    fun onAttach(view : T)
}