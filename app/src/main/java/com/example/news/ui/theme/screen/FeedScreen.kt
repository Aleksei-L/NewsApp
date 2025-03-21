package com.example.news.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.news.data.Article
import com.example.news.ui.theme.NewsCard
import com.example.news.viewmodel.MainViewModel

@Composable
fun FeedScreen(
	vm: MainViewModel,
	onCardClick: (Article) -> Unit
) {
	val news by vm.news.observeAsState()

	Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
		LazyColumn(modifier = Modifier.padding(innerPadding)) {
			items(news?.articles ?: return@LazyColumn) { item ->
				NewsCard(item.title, item.urlToImage) {
					onCardClick(item)
				}
			}
		}
	}
}