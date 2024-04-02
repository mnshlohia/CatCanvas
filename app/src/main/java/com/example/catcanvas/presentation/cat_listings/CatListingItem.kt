package com.example.catcanvas.presentation.cat_listings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.catcanvas.domain.model.CatListingItem


@Composable
fun CatListingItem(
    cats: CatListingItem,
    modifier: Modifier = Modifier
) {
    // URL of the image to load
    Box(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        val imageUrl = cats.url
        SubcomposeAsyncImage(
            model = imageUrl,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio((cats.width.toFloat() / cats.height.toFloat())),
            contentDescription = "(${cats.url})",
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth() // Fill the width of the parent or adjust as needed
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp) // Set the size of the CircularProgressIndicator
                    )
                }
            }
        )
    }
}