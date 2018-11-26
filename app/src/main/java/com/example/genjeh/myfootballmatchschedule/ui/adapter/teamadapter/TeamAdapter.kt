package com.example.genjeh.myfootballmatchschedule.ui.adapter.teamadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.team_item.*

class TeamAdapter(private val context: Context?, private val listTeam: List<Team>, private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))

    override fun getItemCount() = listTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(listTeam[position], listener)
    }
}

class TeamViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindItem(items: Team, listener: (Team) -> Unit) {
        Glide.with(itemView).load(items.teamBadge).into(bannerTeam)
        nameTeam.text = items.nameTeam
        formedYearTeam.text = items.formedYearTeam

        itemView.setOnClickListener {
            listener(items)
        }
    }
}