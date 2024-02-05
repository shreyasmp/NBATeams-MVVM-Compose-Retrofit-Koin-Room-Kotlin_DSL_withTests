package com.shreyasmp.nbateams.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shreyasmp.nbateams.model.Team

@Composable
fun TeamDetailedListView(
    paddingValues: PaddingValues,
    team: Team
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            TeamListItem(team = team, enabledClickable = false, navigateToDetailView = {})

            Spacer(modifier = Modifier.height(8.dp))

            PlayerList(
                paddingValues,
                team.players
            )
        }
    }
}
