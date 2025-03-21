package com.example.news.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
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
							selected = false,//selectedItem == index,
							onClick = {
								//selectedItem = index
								navController.navigate(item.title) //{
								/*navController.graph.startDestinationRoute?.let { route ->
									popUpTo(route) {
										saveState = true
									}
								}
								launchSingleTop = true
								restoreState = true*/
								//}
							}
						)
					}
				}
			}
		) { innerPadding ->
			Column(modifier=Modifier.padding(innerPadding)) {
				var text by remember { mutableStateOf("") }

				Row(modifier = Modifier.fillMaxWidth()) {
					TextField(text, { text = it })
					Button({ vm.searchNews(text) }) {
						Text("Search")
					}
				}

				val searchResults by vm.search.observeAsState()
				LazyColumn(
					//modifier = Modifier.padding(innerPadding)
				) {
					items(searchResults?.articles ?: return@LazyColumn) { item ->
						NewsCard(item.title, item.urlToImage) { onCardClick(item) }
					}
				}
			}
		}
	}
}
