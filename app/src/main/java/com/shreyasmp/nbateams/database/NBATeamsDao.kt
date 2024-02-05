package com.shreyasmp.nbateams.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NBATeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNBATeams(teams: List<TeamsListTable>)

    @Query("Select * from TeamsTable")
    fun queryNBATeams(): List<TeamsListTable>

    @Query("Select * from TeamsTable where id =:teamID")
    fun queryNBATeamById(teamID: Int): TeamsListTable
}