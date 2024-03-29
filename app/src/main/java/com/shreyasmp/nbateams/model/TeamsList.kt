package com.shreyasmp.nbateams.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsList(
    val teams: List<Team> = emptyList()
) : Parcelable
