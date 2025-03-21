package com.example.news.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.news.data.Article
import com.example.news.ui.theme.NewsCard
import com.example.news.ui.theme.NewsTheme
import com.example.news.ui.theme.navigation.items
import com.example.news.viewmodel.MainViewModel

@Composable
fun SearchScreen(
	vm: MainViewModel,
	navController: NavController,
	onCardClick: (Article) -> Unit
) {
	NewsTheme {
		Scaffold(
			bottomBar = {
				NavigationBar {
					items.forEach { item ->
						NavigationBarItem(
							alwaysShowLabel = true,
							icon = {
								androidx.compose.material3.Icon(
									item.icon,
									contentDescription = null
								)
							},
							label = { Text(item.title) },
							selected = false,
							onClick = { navController.navigate(item.title) }
						)
					}
				}
			}
		) { innerPadding ->
			val searchResults by vm.search.observeAsState()
			val needPB by vm.needPBForSearch.observeAsState()

			Column(modifier=Modifier.padding(innerPadding)) {
				var text by remember { mutableStateOf("") }

				if (needPB == true) {
					LinearProgressIndicator(
						modifier = Modifier.fillMaxWidth()
					)
				}

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically
				) {
					TextField(text, { text = it }, modifier = Modifier.weight(1f))
					Button(
						{ vm.searchNews(text) },
						modifier = Modifier.padding(horizontal = 6.dp)
					) {
						Text("Search")
					}
				}

				LazyColumn(
					verticalArrangement = Arrangement.spacedBy(20.dp)
				) {
					items(searchResults?.articles ?: return@LazyColumn) { item ->
						NewsCard(item.title, item.description ?: "", item.urlToImage) {
							onCardClick(
								item
							)
						}
					}
				}
			}
		}
	}
}
