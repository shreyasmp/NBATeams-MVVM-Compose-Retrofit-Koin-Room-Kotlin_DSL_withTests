package com.shreyasmp.nbateams.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.ui.theme.NBATeamsTheme
import com.shreyasmp.nbateams.utils.TeamsDataProvider

@Composable
fun TeamListItem(
    team: Team,
    enabledClickable: Boolean = false,
    navigateToDetailView: (Team) -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .clickable {
                    if (enabledClickable) {
                        navigateToDetailView(team)
                    }
                }
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                DrawComposableText(
                    content = team.full_name,
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Bold,
                    textSize = 20.sp,
                    styleOfText = MaterialTheme.typography.h4,
                    maxLine = 3,
                    tag = "teamName"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawComposableText(
                    content = "Wins",
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 16.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "winsLabel"
                )

                Spacer(modifier = Modifier.height(8.dp))

                DrawComposableText(
                    content = team.wins.toString(),
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 16.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "teamWins"
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawComposableText(
                    content = "Losses",
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 16.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "lossesLabel"
                )

                Spacer(modifier = Modifier.height(8.dp))

                DrawComposableText(
                    content = team.losses.toString(),
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 16.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "teamLosses"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun TeamListItemPreview() {
    NBATeamsTheme {
        TeamListItem(
            TeamsDataProvider.teamData,
            navigateToDetailView = {}
        )
    }
}

@Composable
private fun DrawComposableText(
    content: String?,
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
    weightOfFont: FontWeight?,
    textSize: TextUnit,
    styleOfText: TextStyle,
    maxLine: Int,
    tag: String
) {
    if (content != null) {
        Text(
            text = content,
            modifier = Modifier
                .padding(start, top, end, bottom)
                .testTag(tag),
            color = MaterialTheme.colors.surface,
            fontWeight = weightOfFont,
            fontSize = textSize,
            style = styleOfText,
            maxLines = maxLine
        )
    }
}