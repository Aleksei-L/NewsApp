package com.example.news.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.news.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
	title: String,
	desc: String,
	urlToImage: String?,
	onCardClick: () -> Unit
) {
	Card(onClick = onCardClick) {
		Column {
			Text(text = title, maxLines = 1)
			Spacer(modifier = Modifier.height(8.dp))
			Text(text = desc, maxLines = 2)
			Spacer(modifier = Modifier.height(8.dp))
			GlideImage(
				model = urlToImage ?: "https://google.com",
				contentDescription = null,
				loading = placeholder(R.drawable.ic_placeholder),
				failure = placeholder(R.drawable.ic_placeholder),
				modifier = Modifier.height(300.dp).fillMaxWidth(),
				contentScale = ContentScale.Crop
			)
		}
	}
}
