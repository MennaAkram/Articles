package com.menna.supporttest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.menna.supporttest.ui.theme.Black60
import com.menna.supporttest.ui.theme.Typography

@Composable
fun EmptyPlaceholder(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painter, contentDescription = "Search Placeholder",
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f)
        )
        Text(text = text, style = Typography.titleMedium, color = Black60, modifier = Modifier.padding(top = 16.dp))
    }
}