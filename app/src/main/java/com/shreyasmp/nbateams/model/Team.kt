package com.shreyasmp.nbateams.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val wins: Int? = null,
    val losses: Int? = null,
    val full_name: String? = null,
    val id: Int? = null,
    val players: MutableList<Player>? = null
) : Parcelable
