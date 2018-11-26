package com.example.genjeh.myfootballmatchschedule.ui.adapter.matchadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.db.FavoriteMatch
import com.example.genjeh.myfootballmatchschedule.utils.Util
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_event_item.*

class FavoriteMatchAdapter(private val context: Context?, private val listFavorite: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
            FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.match_event_item, parent, false))


    override fun getItemCount() = listFavorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(listFavorite[position], listener)
    }
}

class FavoriteViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(items: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        dateEvent.text = Util.convertFormatDate(items.dateEvent)
        nameHomeTeam.text = items.nameHomeTeam
        timeEvent.text =  Util.getGmtTime(items.timeEvent?.split("+")?.get(0)+"+00:00","7")
        if(items.scoreHomeTeam == null){
            scoreHomeTeam.text = "-"
        }else{
            scoreHomeTeam.text = items.scoreHomeTeam.toString()
        }

        nameAwayTeam.text = items.nameAwayTeam
        if(items.scoreAwayTeam == null){
            scoreAwayTeam.text = "-"
        }else{
            scoreAwayTeam.text = items.scoreAwayTeam.toString()
        }

        itemView.setOnClickListener {
            listener(items)
        }
    }
}
