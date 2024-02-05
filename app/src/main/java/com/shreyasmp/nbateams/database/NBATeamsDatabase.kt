package com.shreyasmp.nbateams.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TeamsListTable::class], version = 1, exportSchema = false)
abstract class NBATeamsDatabase : RoomDatabase() {
    abstract fun getNBATeamsDao(): NBATeamsDao
}