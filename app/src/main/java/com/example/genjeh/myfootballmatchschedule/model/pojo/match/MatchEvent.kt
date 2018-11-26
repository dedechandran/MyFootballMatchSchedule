package com.example.genjeh.myfootballmatchschedule.model.pojo.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchEvent(
        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var timeEvent: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("strHomeTeam")
        var nameHomeTeam: String? = null,

        @SerializedName("intHomeScore")
        var scoreHomeTeam: String? = null,

        @SerializedName("strHomeGoalDetails")
        var goalsDetailHomeTeam: String? = null,

        @SerializedName("intHomeShots")
        var shootsHomeTeam: String? = null,

        @SerializedName("strHomeYellowCards")
        var yellowCardsHomeTeam: String? = null,

        @SerializedName("strHomeRedCards")
        var redCardsHomeTeam: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var goalKeeperHomeTeam: String? = null,

        @SerializedName("strHomeLineupDefense")
        var defenseHomeTeam: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var midfieldHomeTeam: String? = null,

        @SerializedName("strHomeLineupForward")
        var forwardHomeTeam: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var substitutesHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,

        @SerializedName("strAwayTeam")
        var nameAwayTeam: String? = null,

        @SerializedName("intAwayScore")
        var scoreAwayTeam: String? = null,

        @SerializedName("strAwayGoalDetails")
        var goalsDetailAwayTeam: String? = null,

        @SerializedName("intAwayShots")
        var shootsAwayTeam: String? = null,

        @SerializedName("strAwayYellowCards")
        var yellowCardsAwayTeam: String? = null,

        @SerializedName("strAwayRedCards")
        var redCardsAwayTeam: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var goalKeeperAwayTeam: String? = null,

        @SerializedName("strAwayLineupDefense")
        var defenseAwayTeam: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var midfieldAwayTeam: String? = null,

        @SerializedName("strAwayLineupForward")
        var forwardAwayTeam: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var substitutesAwayTeam: String? = null

) : Parcelable