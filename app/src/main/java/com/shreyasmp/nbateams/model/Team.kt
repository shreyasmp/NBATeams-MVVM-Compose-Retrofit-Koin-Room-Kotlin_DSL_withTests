package com.shreyasmp.nbateams.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shreyasmp.nbateams.database.PlayersListTypeConverter
import kotlinx.parcelize.Parcelize

@Entity(tableName = "NBATeamsTable")
@Parcelize
class Team(
    val wins: Int = 0,
    val losses: Int = 0,
    val full_name: String = "",
    @PrimaryKey
    val id: Int = 0,
    @TypeConverters(PlayersListTypeConverter::class)
    val players: List<Player> = emptyList()
) : Parcelable
