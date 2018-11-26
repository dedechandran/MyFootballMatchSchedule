package com.example.genjeh.myfootballmatchschedule.model.retrofit

import com.example.genjeh.myfootballmatchschedule.model.pojo.league.LeagueResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEventQueryResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.match.MatchEventResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.player.PlayerResponse
import com.example.genjeh.myfootballmatchschedule.model.pojo.team.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/api/v1/json/1/eventspastleague.php?")
    fun getLastMatch(@Query("id") idLeague: Int?): Call<MatchEventResponse>

    @GET("/api/v1/json/1/eventsnextleague.php?")
    fun getNextMatch(@Query("id") idLeague: Int?): Call<MatchEventResponse>

    @GET("/api/v1/json/1/lookupevent.php?")
    fun getDetailEvent(@Query("id") idEvent: Int?): Call<MatchEventResponse>

    @GET("/api/v1/json/1/lookup_all_teams.php?")
    fun getTeam(@Query("id") idLeague: Int?): Call<TeamResponse>

    @GET("/api/v1/json/1/lookupteam.php?")
    fun getDetailTeam(@Query("id") idTeam: Int?): Call<TeamResponse>

    @GET("/api/v1/json/1/lookup_all_players.php?")
    fun getPlayer(@Query("id") idTeam: Int?): Call<PlayerResponse>

    @GET("/api/v1/json/1/all_leagues.php?")
    fun getLeague(): Call<LeagueResponse>

    @GET("/api/v1/json/1/searchteams.php?")
    fun searchTeam(@Query("t") query: String?): Call<TeamResponse>

    @GET("/api/v1/json/1/searchevents.php?")
    fun searchEvent(@Query("e") query: String?): Call<MatchEventQueryResponse>


}