package com.shreyasmp.nbateams.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shreyasmp.nbateams.model.Team

@Composable
fun TeamList(
    padding: PaddingValues,
    teamsData: MutableList<Team>?,
    navigateToDetailView: (Team) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        teamsData?.let {
            it.sortBy { team ->
                team.full_name
            }.run {
                items(it) { team ->
                    TeamListItem(
                        team = team,
                        enabledClickable = true,
                        navigateToDetailView = navigateToDetailView
                    )
                }
            }
        }
    }
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            CircularProgressIndicator()
        }
    }
}