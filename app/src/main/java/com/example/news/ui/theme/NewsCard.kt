package com.example.news.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
	title: String,
	urlToImage: String?,
	onCardClick: () -> Unit
) {
	Card(onClick = onCardClick) {
		Column {
			Text(title)
			GlideImage(urlToImage ?: "https://google.com", contentDescription = null)
		}
	}
}