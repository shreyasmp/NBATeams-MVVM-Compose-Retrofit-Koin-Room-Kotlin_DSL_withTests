package com.shreyasmp.nbateams.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Int = 0,
    val first_name: String = "",
    val last_name: String = "",
    val position: String = "",
    val number: Int = 0,
) : Parcelable