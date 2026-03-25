package com.ucb.app.movie.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ucb.app.movie.domain.model.MovieModel

@Composable
fun CardMovie(
    model: MovieModel,
    onRatingClick: (Int) -> Unit,
    onDetailClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {

            AsyncImage(
                model = model.pathUrl,             // 1. The Source
                contentDescription = model.title,   // 2. Accessibility
                contentScale = ContentScale.Crop,   // 3. Fitting Strategy
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)          // 4. Layout en formato poster
                    .clickable { onDetailClick() }  // el click a descripcion
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(
                    rating = model.rating,
                    onRatingSelected = { selectedStars ->
                        onRatingClick(selectedStars)
                    }
                )
            }
        }
    }
}
