package com.example.genjeh.myfootballmatchschedule.model.db

class FavoriteTeam(val id : Int?,
                   val idTeam : String?,
                   val nameTeam : String?,
                   val teamBadge : String?,
                   val yearTeam : String?,
                   val stadiumTeam : String?,
                   val descTeam : String?) {
    companion object {
        const val TABLE_NAME = "FavoriteTeam"
        const val ID = "ID_"
        const val ID_TEAM = "ID_TEAM"
        const val NAME_TEAM = "NAME_TEAM"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val YEAR_TEAM = "YEAR_TEAM"
        const val STADIUM_TEAM = "STADIUM_TEAM"
        const val DESC_TEAM = "DESC_TEAM"
    }

}