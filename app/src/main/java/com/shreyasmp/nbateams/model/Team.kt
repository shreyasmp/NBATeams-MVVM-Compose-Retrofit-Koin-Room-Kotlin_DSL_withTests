package com.shreyasmp.nbateams.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val wins: Int = 0,
    val losses: Int = 0,
    val full_name: String = "",
    val id: Int = 0,
    val players: List<Player> = emptyList()
) : Parcelable
