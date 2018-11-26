package com.example.genjeh.myfootballmatchschedule.model.pojo.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        @SerializedName("idTeam")
        var idTeam : String? = null,

        @SerializedName("strTeam")
        var nameTeam : String? = null,

        @SerializedName("intFormedYear")
        var formedYearTeam : String? = null,

        @SerializedName("strStadium")
        var stadiumTeam : String? = null,

        @SerializedName("strDescriptionEN")
        var descriptionTeam : String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null

) : Parcelable