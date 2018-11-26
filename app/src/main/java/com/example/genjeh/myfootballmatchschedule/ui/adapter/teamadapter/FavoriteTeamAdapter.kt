package com.example.genjeh.myfootballmatchschedule.ui.adapter.teamadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteTeam
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.team_item.*

class FavoriteTeamAdapter(private val context: Context?, private val listTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<FavoriteTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
            FavoriteTeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))

    override fun getItemCount() = listTeam.size

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(listTeam[position], listener)
    }
}

class FavoriteTeamViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindItem(items: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Glide.with(itemView).load(items.teamBadge).into(bannerTeam)
        nameTeam.text = items.nameTeam
        formedYearTeam.text = items.yearTeam
        itemView.setOnClickListener {
            listener(items)
        }
    }

}
