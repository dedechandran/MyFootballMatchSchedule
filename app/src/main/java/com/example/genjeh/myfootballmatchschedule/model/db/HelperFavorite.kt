package com.example.genjeh.myfootballmatchschedule.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class HelperFavorite(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favoriteMatch.db", null, 1) {
    companion object {
        private var instance: HelperFavorite? = null

        @Synchronized
        fun getInstance(ctx: Context): HelperFavorite {
            if (instance == null) {
                instance = HelperFavorite(ctx.applicationContext)
            }
            return instance as HelperFavorite
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_NAME, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.TIME_EVENT to TEXT,
                FavoriteMatch.ID_HOME_TEAM to TEXT,
                FavoriteMatch.NAME_HOME_TEAM to TEXT,
                FavoriteMatch.SCORE_HOME_TEAM to TEXT,
                FavoriteMatch.ID_AWAY_TEAM to TEXT,
                FavoriteMatch.NAME_AWAY_TEAM to TEXT,
                FavoriteMatch.SCORE_AWAY_TEAM to TEXT
        )
        db?.createTable(FavoriteTeam.TABLE_NAME, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavoriteTeam.NAME_TEAM to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.YEAR_TEAM to TEXT,
                FavoriteTeam.STADIUM_TEAM to TEXT,
                FavoriteTeam.DESC_TEAM to TEXT
                )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_NAME, true)
        db?.dropTable(FavoriteTeam.TABLE_NAME, true)
    }
}

val Context.database: HelperFavorite
    get() = HelperFavorite.getInstance(applicationContext)