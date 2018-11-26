package com.example.genjeh.myfootballmatchschedule.model.db

data class FavoriteMatch(val id: Int?,
                         val idEvent: String?,
                         val dateEvent: String?,
                         val timeEvent: String?,
                         val idHomeTeam: String?,
                         val nameHomeTeam: String?,
                         val scoreHomeTeam: String?,
                         val idAwayTeam: String?,
                         val nameAwayTeam: String?,
                         val scoreAwayTeam: String?) {
    companion object {
        const val TABLE_NAME = "FavoriteMatch"
        const val ID = "ID_"
        const val ID_EVENT = "ID_EVENT"
        const val DATE_EVENT = "DATE_EVENT"
        const val TIME_EVENT = "TIME_EVENT"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val NAME_HOME_TEAM = "NAME_HOME_TEAM"
        const val SCORE_HOME_TEAM = "SCORE_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val NAME_AWAY_TEAM = "NAME_AWAY_TEAM"
        const val SCORE_AWAY_TEAM = "SCORE_AWAY_TEAM"
    }
}