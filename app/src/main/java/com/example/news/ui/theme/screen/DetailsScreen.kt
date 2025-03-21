package com.example.news.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
	title: String,
	desc: String,
	urlToImage: String
) {
	Column(modifier = Modifier.fillMaxSize()) {
		GlideImage(urlToImage, contentDescription = null)
		Text(title)
		Text(desc)
	}
}
