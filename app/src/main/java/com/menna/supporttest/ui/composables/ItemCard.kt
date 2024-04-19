package com.menna.supporttest.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.menna.supporttest.R
import com.menna.supporttest.ui.theme.Black37
import com.menna.supporttest.ui.theme.Black60
import com.menna.supporttest.ui.theme.Border
import com.menna.supporttest.ui.theme.Primary
import com.menna.supporttest.ui.theme.Shapes
import com.menna.supporttest.ui.theme.Typography

@Composable
fun ItemCard(
    imageUrl: String,
    url: String,
    name: String,
    author: String,
    title: String,
    description: String,
    date: String,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Border, Shapes.large)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = imageUrl, contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium)
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = name, style = Typography.titleLarge)
            Icon(
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    context.startActivity(intent)
                },
                painter = painterResource(id = R.drawable.link),
                tint = Black60,
                contentDescription = "Link"
            )
        }
        Text(text = author, style = Typography.titleMedium)
        Text(text = title, style = Typography.bodyLarge)
        Text(text = description, style = Typography.bodySmall)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = date, style = Typography.bodyMedium)
            Icon(
                modifier = Modifier.clickable {
                    onFavoriteClick()
                },
                painter = painterResource(if (isFavorite) R.drawable.heart_fill else R.drawable.heart),
                tint = if (isFavorite) Primary else Black37,
                contentDescription = "Link"
            )
        }
    }
}