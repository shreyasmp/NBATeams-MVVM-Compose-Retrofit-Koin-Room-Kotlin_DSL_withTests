package com.shreyasmp.nbateams.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Int? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val position: String? = null,
    val number: Int? = null,
) : Parcelable