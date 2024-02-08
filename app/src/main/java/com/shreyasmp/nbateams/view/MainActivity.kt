package com.shreyasmp.nbateams.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NBATeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TeamListView(nbaTeamViewModel) {
                        // On tap of list item, show the details with player
                        // list that needs to be passed to detail screen.
                        startActivity(DetailedViewActivity.newIntent(this, it))
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
                teamListData as MutableList<Team>?,
                navigateToDetailView,
                isLoading
            )
        }
    )
}