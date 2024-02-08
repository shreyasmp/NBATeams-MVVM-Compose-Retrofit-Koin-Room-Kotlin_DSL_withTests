package com.shreyasmp.nbateams.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shreyasmp.nbateams.model.Team

@Dao
interface NBATeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNBATeams(teams: List<Team>)

    @Query("Select * from NBATeamsTable")
    fun queryNBATeams(): List<Team>
}