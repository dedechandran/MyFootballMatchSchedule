package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam.player


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import com.example.genjeh.myfootballmatchschedule.model.retrofit.ApiRepository
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitInstance
import com.example.genjeh.myfootballmatchschedule.model.retrofit.RetrofitService
import com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailplayer.DetailPlayerActivity
import com.example.genjeh.myfootballmatchschedule.ui.adapter.playeradapter.PlayerAdapter
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.jetbrains.anko.toast


class PlayerFragment : Fragment(), PlayerView {
    override fun onShowToast(message: String) {
        context?.toast(message)?.show()
    }

    override fun onShowLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh.isRefreshing = false
    }

    companion object {
        const val FRAGMENT_NAME = "Player"

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_ID_TEAM = "id_team"
        private const val ARG_FRAGMENT_NAME = "fragment_name"

        fun newInstance(sectionNumber: Int, idTeam: Int, fragmentName: String): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            args.putInt(ARG_ID_TEAM, idTeam)
            args.putString(ARG_FRAGMENT_NAME, fragmentName)

            fragment.arguments = args
            return fragment
        }
    }


    override fun onLoadPlayer(data: List<Player>) {
        listPlayer.clear()
        listPlayer.addAll(data)
        adapter.notifyDataSetChanged()
    }


    private lateinit var adapter: PlayerAdapter
    private lateinit var presenter: PlayerPresenter
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private val listPlayer: MutableList<Player> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        swipeRefresh = view.swipeRefreshPlayer
        swipeRefresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark)

        adapter = PlayerAdapter(context, listPlayer) {
            val intent = Intent(context, DetailPlayerActivity::class.java)
            intent.putExtra(DetailPlayerActivity.ARG_PLAYER, it)
            startActivity(intent)
        }

        view.listPlayer.adapter = adapter
        view.listPlayer.layoutManager = LinearLayoutManager(context)
        val idTeam = arguments?.getInt(ARG_ID_TEAM)

        val service = RetrofitInstance.newInstance()?.create(RetrofitService::class.java)
        val apiRepo = ApiRepository(service)
        presenter = PlayerPresenter(context, apiRepo)
        presenter.onAttach(this)
        presenter.loadPlayer(idTeam)

        swipeRefresh.setOnRefreshListener {
            presenter.loadPlayer(idTeam)
        }



        return view
    }


}
