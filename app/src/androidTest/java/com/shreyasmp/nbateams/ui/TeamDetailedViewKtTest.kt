package com.shreyasmp.nbateams.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shreyasmp.nbateams.model.Player
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.ui.theme.NBATeamsTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamDetailedViewKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockTestData: Team

    @Before
    fun setup() {
        val player1 = Player(
            id = 45,
            first_name = "Micheal",
            last_name = "Jordan",
            position = "C",
            number = 10
        )
        val player2 = Player(
            id = 55,
            first_name = "Shaquille",
            last_name = "O'Neal",
            position = "SG",
            number = 7
        )
        mockTestData = Team(
            wins = 45,
            losses = 20,
            full_name = "Toronto Raptors",
            id = 19,
            players = mutableListOf(player1, player2)
        )
        composeTestRule.setContent {
            NBATeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TeamListItem(team = mockTestData, navigateToDetailView = {})
                }
            }
        }
    }

    @Test
    fun testTeamDetailedViewComposable() {
        composeTestRule.run {
            onNodeWithText("Wins").assertIsDisplayed()
            onNodeWithText("Losses").assertIsDisplayed()
        }
    }
}