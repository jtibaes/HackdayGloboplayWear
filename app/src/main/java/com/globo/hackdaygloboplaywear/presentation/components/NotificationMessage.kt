package com.globo.hackdaygloboplaywear.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import com.globo.hackdaygloboplaywear.R

@Composable
fun NotificationMessage(greetingName: String, onClickGP: () -> Unit, onClickBets: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFFDB5103),
            text = stringResource(R.string.hello_world, greetingName)
        )

        Row {
            Button(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 20.dp)
                    .size(ButtonDefaults.SmallButtonSize),
                onClick = onClickGP,
            ) {
                Text(text = "GP")
            }
            Button(
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp, bottom = 20.dp)
                    .size(ButtonDefaults.SmallButtonSize),
                onClick = onClickBets,
            ) {
                Text(text = "Bets")
            }
        }
    }
}

@WearPreviewLargeRound
@WearPreviewSmallRound
@WearPreviewSquare
@Composable
fun DefaultPreview() {
    NotificationMessage("Preview Android", {}, {})
}