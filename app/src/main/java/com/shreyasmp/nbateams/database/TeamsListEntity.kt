package com.shreyasmp.nbateams.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.shreyasmp.nbateams.model.Player
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TeamsTable")
@Parcelize
class TeamsListTable() : Parcelable {

    @PrimaryKey
    @Expose
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @ColumnInfo(name = "wins")
    var wins: Int = 0

    @Expose
    @ColumnInfo(name = "losses")
    var losses: Int = 0

    @Expose
    @ColumnInfo(name = "fullName")
    var full_name: String = ""

    @Expose
    @TypeConverters(PlayersListTypeConverter::class)
    @ColumnInfo(name = "players")
    var players: List<Player> = listOf()

    constructor(id: Int, wins: Int, losses: Int, fullName: String, players: List<Player>) : this() {
        this.id = id
        this.wins = wins
        this.losses = losses
        this.full_name = fullName
        this.players = players
    }
}