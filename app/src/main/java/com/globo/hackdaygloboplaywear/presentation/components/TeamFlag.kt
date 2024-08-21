package com.globo.hackdaygloboplaywear.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.globo.hackdaygloboplaywear.R

@Composable
fun TeamFlag(
    teamName: String,
    url: String,
    flagSize: Dp = 48.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = teamName,
            style = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.caption1.fontSize,
                shadow = Shadow(color = Color.Black, blurRadius = 1f)
            )
        )

        AsyncImage(
            modifier = Modifier
                .size(flagSize),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
//                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_flag_placeholder)
        )
    }
}

@Preview
@Composable
fun TeamFlagPreview() {
    Row {
        TeamFlag("FLA", "")
        TeamFlag("FLA", "", 32.dp)
    }
}
