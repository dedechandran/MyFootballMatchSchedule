package com.example.genjeh.myfootballmatchschedule.ui.adapter.playeradapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_item.*

class PlayerAdapter(private val context : Context? ,private val listPlayer : List<Player>,private val listener : (Player) ->Unit) : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
            PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))

    override fun getItemCount() = listPlayer.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(listPlayer[position],listener)
    }
}

class PlayerViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView) , LayoutContainer {
    fun bindItem(items :Player, listener : (Player) -> Unit){
        if (items.cutOutPlayer == null){
            playerImage.setImageResource(R.drawable.ic_person_black_48dp)
        }else{
            Glide.with(itemView).load(items.cutOutPlayer).into(playerImage)
        }

        playerName.text = items.namePlayer
        playerPosition.text = items.positionPlayer
        itemView.setOnClickListener {
            listener(items)
        }
    }

}
