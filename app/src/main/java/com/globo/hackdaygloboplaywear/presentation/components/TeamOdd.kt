package com.globo.hackdaygloboplaywear.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TeamOdd(
    teamName: String,
    teamFlag: String,
    odd: Double,
    flagSize: Dp = 48.dp
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TeamFlag(
            teamName = teamName,
            url = teamFlag,
            flagSize = flagSize
        )

        Spacer(modifier = Modifier.height(6.dp))

        Odd(oddValue = odd)
    }
}

@Preview
@Composable
fun TeamOddPreview() {
    TeamOdd(teamName = "COR", teamFlag = "", odd = 0.82)
}