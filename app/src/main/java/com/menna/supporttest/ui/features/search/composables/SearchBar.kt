package com.menna.supporttest.ui.features.search.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.menna.supporttest.ui.theme.Black37
import com.menna.supporttest.ui.theme.Border
import com.menna.supporttest.ui.theme.Primary
import com.menna.supporttest.ui.theme.Shapes
import com.menna.supporttest.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier.fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = Border,
                shape = Shapes.medium
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            cursorColor = Primary,
            textColor = Black37,
        ),
        placeholder = {
            Text(
                text = "Search...",
                style = Typography.bodyLarge,
                color = Black37
            )
        },
    )
}