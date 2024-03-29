package com.shreyasmp.nbateams.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.ui.TeamDetailedListView
import com.shreyasmp.nbateams.ui.theme.NBATeamsTheme

class DetailedViewActivity : ComponentActivity() {

    private val teamData: Team by lazy {
        intent?.getParcelableExtra(TEAM_DATA, Team::class.java) as Team
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBATeamsTheme {
                TeamDetailView(teamData)
            }
        }
    }

    companion object {
        private const val TEAM_DATA = "team_data"
        fun newIntent(context: Context, teamData: Team?) =
            Intent(context, DetailedViewActivity::class.java).apply {
                putExtra(TEAM_DATA, teamData)
            }
    }
}

@Composable
fun TeamDetailView(
    team: Team
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = team.full_name)
                    }
                }
            )
        },
        content = { padding ->
            TeamDetailedListView(
                padding,
                team
            )
        }
    )
}
