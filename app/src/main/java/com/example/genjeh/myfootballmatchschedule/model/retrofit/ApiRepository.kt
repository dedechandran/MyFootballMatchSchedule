package com.example.genjeh.myfootballmatchschedule.model.retrofit

import com.example.genjeh.myfootballmatchschedule.model.pojo.league.League
import com.example.genjeh.myfootballmatchschedule.model.pojo.league.LeagueResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEvent
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEventQueryResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEventResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.Player
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.PlayerResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.Team
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository(private var service: RetrofitService?) {
    fun lastMatchRepository(leagueId : Int?, callback: RepositoryCallback<MatchEvent>) {
        service?.getLastMatch(leagueId)?.enqueue(object : Callback<MatchEventResponse>{
            override fun onFailure(call: Call<MatchEventResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchEventResponse>, response: Response<MatchEventResponse>) {
                callback.onSuccess(response.body()?.events)
            }
        })
    }

    fun nextMatchRepository(leagueId: Int?,callback: RepositoryCallback<MatchEvent>){
        service?.getNextMatch(leagueId)?.enqueue(object : Callback<MatchEventResponse>{
            override fun onFailure(call: Call<MatchEventResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchEventResponse>, response: Response<MatchEventResponse>) {
                callback.onSuccess(response.body()?.events)
            }

        })
    }

    fun matchDetailRepository(eventId: Int?,callback: RepositoryCallback<MatchEvent>){
        service?.getDetailEvent(eventId)?.enqueue(object : Callback<MatchEventResponse>{
            override fun onFailure(call: Call<MatchEventResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchEventResponse>, response: Response<MatchEventResponse>) {
                callback.onSuccess(response.body()?.events)
            }

        })
    }

    fun teamDetailRepository(teamId : Int?,callback: RepositoryCallback<Team>){
        service?.getDetailTeam(teamId)?.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                callback.onSuccess(response.body()?.teams)
            }
        })
    }

    fun teamRepository(leagueId: Int?,callback: RepositoryCallback<Team>){
        service?.getTeam(leagueId)?.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                callback.onSuccess(response.body()?.teams)
            }

        })
    }


    fun playerRepository(teamId: Int?,callback: RepositoryCallback<Player>){
        service?.getPlayer(teamId)?.enqueue(object  : Callback<PlayerResponse>{
            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                callback.onSuccess(response.body()?.player)
            }

        })
    }

    fun leagueRepository(callback: RepositoryCallback<League>){
        service?.getLeague()?.enqueue(object : Callback<LeagueResponse>{
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                callback.onSuccess(response.body()?.leagues)
            }

        })
    }


    fun searchTeam(query : String?,callback: RepositoryCallback<Team>){
        service?.searchTeam(query)?.enqueue(object  : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                callback.onSuccess(response.body()?.teams)
            }

        })
    }

    fun searchEvent(query : String?,callback: RepositoryCallback<MatchEvent>){
        service?.searchEvent(query)?.enqueue(object  : Callback<MatchEventQueryResponse>{
            override fun onFailure(call: Call<MatchEventQueryResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchEventQueryResponse>, response: Response<MatchEventQueryResponse>) {
                callback.onSuccess(response.body()?.event)
            }

        })
    }


}

interface RepositoryCallback<T>{
    fun onSuccess(data : List<T>?)
    fun onError()
}