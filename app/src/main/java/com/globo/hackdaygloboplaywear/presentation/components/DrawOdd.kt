package com.globo.hackdaygloboplaywear.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun DrawOdd(odd: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "X",
            style = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.title1.fontSize,
                shadow = Shadow(color = Color.Black, blurRadius = 1f)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Odd(oddValue = odd)
    }
}

@Preview
@Composable
fun DrawOddPreview() {
    DrawOdd(odd = 0.4)
}