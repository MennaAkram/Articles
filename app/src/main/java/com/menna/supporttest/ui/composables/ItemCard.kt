package com.menna.supporttest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.menna.supporttest.R
import com.menna.supporttest.ui.theme.Black37
import com.menna.supporttest.ui.theme.Black60
import com.menna.supporttest.ui.theme.Border
import com.menna.supporttest.ui.theme.Shapes
import com.menna.supporttest.ui.theme.Typography

@Composable
fun ItemCard(
    name: String,
    author: String,
    title: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Border, Shapes.large)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().clip(Shapes.medium).aspectRatio(16f / 9f),
            painter = painterResource(R.drawable.image),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = name, style = Typography.titleLarge)
            Icon(painter = painterResource(id = R.drawable.link),
                tint = Black60,
                contentDescription = "Link")
        }
        Text(text = author, style = Typography.titleMedium)
        Text(text = title, style = Typography.bodyLarge)
        Text(text = description, style = Typography.bodySmall)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = date, style = Typography.bodyMedium)
            Icon(painter = painterResource(R.drawable.heart),
                tint = Black37,
                contentDescription = "Link")
        }
    }
}