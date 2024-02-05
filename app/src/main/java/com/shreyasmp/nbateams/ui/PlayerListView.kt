package com.shreyasmp.nbateams.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shreyasmp.nbateams.model.Player

@Composable
fun PlayerList(
    padding: PaddingValues,
    playersList: MutableList<Player>?
) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        playersList?.let {
            items(it) { player ->
                PlayerListItem(player = player)
            }
        }
    }
}