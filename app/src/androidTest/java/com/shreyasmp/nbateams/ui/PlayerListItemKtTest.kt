package com.shreyasmp.nbateams.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shreyasmp.nbateams.model.Player
import com.shreyasmp.nbateams.ui.theme.NBATeamsTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerListItemKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockTestData: Player

    @Before
    fun setup() {
        mockTestData = Player(
            id = 45,
            first_name = "Micheal",
            last_name = "Jordan",
            position = "C",
            number = 10
        )
        composeTestRule.setContent {
            NBATeamsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PlayerListItem(player = mockTestData)
                }
            }
        }
    }

    @Test
    fun testPlayerListItemComposable() {
        composeTestRule.run {
            onNodeWithText("Number").assertIsDisplayed()
            onNodeWithText("Position").assertIsDisplayed()
        }
    }
}