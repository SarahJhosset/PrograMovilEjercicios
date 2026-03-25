package com.ucb.app.movie.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingBar(
    rating: Int,
    onRatingSelected: (Int) -> Unit
) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        for (i in 1..5) {
            val isSelected = i <= rating
            Text(
                // ★ es el caracter lleno, ☆ es el vacío
                text = if (isSelected) "★" else "☆",
                modifier = Modifier
                    .clickable { onRatingSelected(i) }
                    .padding(horizontal = 2.dp),
                // Dorado para las llenas, gris para las vacías
                color = if (isSelected) Color(0xFFFFD700) else Color.LightGray,
                fontSize = 24.sp
            )
        }
    }
}
