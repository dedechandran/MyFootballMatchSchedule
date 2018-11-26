package com.example.genjeh.myfootballmatchschedule.model.pojo.league

import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague")
        var idLeague: String? = null,
        @SerializedName("strLeague")
        var nameLeague: String? = null,
        @SerializedName("strSport")
        var nameSport : String? = null
)