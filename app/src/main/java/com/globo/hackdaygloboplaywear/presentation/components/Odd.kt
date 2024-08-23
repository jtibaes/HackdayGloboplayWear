package com.globo.hackdaygloboplaywear.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun Odd(oddValue: Double) {
    Row(
        modifier = Modifier
            .background(
                color = Color(0xFF2d3745),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .width(36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = oddValue.format(2),
            style = TextStyle(
                fontSize = MaterialTheme.typography.caption2.fontSize,
                color = Color(0xFFa2c774)
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
@Composable
fun OddPreview() {
    Column {
        Odd(1.32)
        Spacer(modifier = Modifier.height(8.dp))
        Odd(0.3)
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)