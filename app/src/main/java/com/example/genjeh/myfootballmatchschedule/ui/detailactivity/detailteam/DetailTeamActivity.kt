package com.example.genjeh.myfootballmatchschedule.ui.detailactivity.detailteam

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team


import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.design.snackbar


class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    companion object {
        const val ARG_TEAM = "team"
    }

    override fun getDataIntent() = intent.getParcelableExtra(ARG_TEAM) as Team

    override fun onShowDetail(data: Team?) {
        Glide.with(this).load(data?.teamBadge).into(bannerDetailTeam)
        nameDetailTeam.text = data?.nameTeam
        yearDetailTeam.text = data?.formedYearTeam
        stadiumDetailTeam.text = data?.stadiumTeam
    }


    override fun setFavoriteIcon(isFav: Boolean) {
        val favoriteOption = optionMenu?.getItem(0)
        if (isFav) favoriteOption?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_white_24dp) else favoriteOption?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_border_white_24dp)
    }

    override fun onShowSnackBar(message: String) {
        snackbar(coordinatorDetailTeam, message).show()
    }


    private lateinit var presenter: DetailTeamPresenter
    private var optionMenu: Menu? = null
    private var isFavorite = false
    var data : Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(detailTeamToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        data = getDataIntent()
        presenter = DetailTeamPresenter(applicationContext)
        presenter.onAttach(this)
        presenter.showTeamDetail(data)

        val adapter = DetailTeamPagerAdapter(data?.descriptionTeam, data?.idTeam, supportFragmentManager)
        viewPagerDetailTeam.adapter = adapter
        tabDetailTeam.setupWithViewPager(viewPagerDetailTeam)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        optionMenu = menu
        isFavorite = presenter.checkFavoriteMatch(data?.idTeam.toString())
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.favorite_option_menu ->
                isFavorite = if (!isFavorite) {
                    presenter.saveToFavoriteDb(data)
                    true
                } else {
                    presenter.removeFromFavoriteDb(data?.idTeam.toString())
                    false
                }
        }
        return super.onOptionsItemSelected(item)
    }
}
