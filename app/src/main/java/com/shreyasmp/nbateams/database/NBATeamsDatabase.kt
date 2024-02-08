package com.shreyasmp.nbateams.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shreyasmp.nbateams.model.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
@TypeConverters(PlayersListTypeConverter::class)
abstract class NBATeamsDatabase : RoomDatabase() {
    abstract fun getNBATeamsDao(): NBATeamsDao
}