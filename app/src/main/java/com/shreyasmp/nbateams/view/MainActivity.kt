package com.shreyasmp.nbateams.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.ui.TeamList
import com.shreyasmp.nbateams.ui.theme.NBATeamsTheme
import com.shreyasmp.nbateams.viewmodel.NBATeamsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val nbaTeamViewModel by viewModel<NBATeamsViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NBATeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TeamListView(nbaTeamViewModel) {
                        // On tap of list item, make call with team name for fetching team
                        // details with player list that needs to be passed to detail screen.
                        it.full_name?.let { teamName ->
                            nbaTeamViewModel.fetchNBATeamDetails(teamName)
                        }
                        nbaTeamViewModel.nbaTeamDetailResponse.observe(this) { teamData ->
                            startActivity(DetailedViewActivity.newIntent(this, teamData))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TeamListView(
    viewModel: NBATeamsViewModel,
    navigateToDetailView: (Team) -> Unit
) {

    val teamListData by viewModel.nbaTeamsListResponse.observeAsState()
    val isLoading by viewModel.isLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "NBA Teams")
                    }
                }
            )
        },
        content = { padding ->
            TeamList(
                padding,
                teamListData?.teams,
                navigateToDetailView,
                isLoading
            )
        }
    )
}