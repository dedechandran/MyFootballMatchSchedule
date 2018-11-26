package com.example.genjeh.myfootballmatchschedule.model.pojo.player

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("idPlayer")
        var idPlayer : String? = null,

        @SerializedName("strPlayer")
        var namePlayer : String? = null,

        @SerializedName("strFanart1")
        var imgPlayer : String? = null,

        @SerializedName("strCutout")
        var cutOutPlayer : String? = null,

        @SerializedName("strWeight")
        var weightPlayer : String? = null,

        @SerializedName("strHeight")
        var heightPlayer : String? = null,

        @SerializedName("strPosition")
        var positionPlayer : String? = null,

        @SerializedName("strDescriptionEN")
        var descriptionPlayer : String? = null
) : Parcelable