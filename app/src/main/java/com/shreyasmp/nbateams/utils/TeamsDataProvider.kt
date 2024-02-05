package com.shreyasmp.nbateams.utils

import com.shreyasmp.nbateams.model.Player
import com.shreyasmp.nbateams.model.Team

object TeamsDataProvider {

    val teamData = Team(
        wins = 45,
        losses = 20,
        full_name = "Boston Celtics",
        id = 1
    )

    val player1 = Player(
        id = 3578,
        first_name = "Micheal",
        last_name = "Jordan",
        position = "C",
        number = 10
    )

    val player2 = Player(
        id = 4567,
        first_name = "Micheal",
        last_name = "Tordan",
        position = "SG",
        number = 7
    )

    val teamDetailData = Team(
        wins = 45,
        losses = 20,
        full_name = "Boston Celtics",
        id = 1,
        players = mutableListOf(player1, player2)
    )
}