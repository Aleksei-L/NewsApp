package com.example.news.ui.theme.screen

import androidx.compose.runtime.Composable
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewState

@Composable
fun DetailsScreen(
	url: String?
) {
	val state = rememberWebViewState(url ?: "https://google.com")
	WebView(state)
}
