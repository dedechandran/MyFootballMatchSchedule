package com.example.genjeh.myfootballmatchschedule.ui.adapter.matchadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.utils.Util
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_event_item.*

class MatchEventAdapter(private val context : Context?, private val lastMatch : List<MatchEvent>, private val listener : (MatchEvent) -> Unit) : RecyclerView.Adapter<MatchEventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
            MatchEventViewHolder(LayoutInflater.from(context).inflate(R.layout.match_event_item, parent, false))

    override fun getItemCount() = lastMatch.size

    override fun onBindViewHolder(holder: MatchEventViewHolder, position: Int) {
        holder.bindItem(lastMatch[position],listener)
    }
}

class MatchEventViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView) , LayoutContainer {
    fun bindItem(items : MatchEvent, listener : (MatchEvent) -> Unit){
        dateEvent.text = Util.convertFormatDate(items.dateEvent)
        timeEvent.text = Util.getGmtTime(items.timeEvent?.split("+")?.get(0)+"+00:00","7")
        nameHomeTeam.text = items.nameHomeTeam
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
