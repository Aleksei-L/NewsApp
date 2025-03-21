package com.example.news.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.news.data.Article
import com.example.news.ui.theme.NewsCard
import com.example.news.ui.theme.NewsTheme
import com.example.news.ui.theme.navigation.items
import com.example.news.viewmodel.MainViewModel

@Composable
fun FeedScreen(
	vm: MainViewModel,
	navController: NavController,
	onCardClick: (Article) -> Unit
) {
	val news by vm.news.observeAsState()

	NewsTheme {
		Scaffold(
			modifier = Modifier.fillMaxSize(),
			bottomBar = {
				/*var selectedItem by remember { mutableIntStateOf(0) }
			items.forEachIndexed { index, navigationItem ->
				if (navigationItem.rootRoute.route == currentRoute.value) {
					selectedItem = index
				}
			}*/
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
								}*//*
								launchSingleTop = true
								restoreState = true*/
								//}
							}
						)
					}
				}
			}
		) { innerPadding ->
			LazyColumn(modifier = Modifier.padding(innerPadding)) {
				items(news?.articles ?: return@LazyColumn) { item ->
					NewsCard(item.title, item.urlToImage) {
						onCardClick(item)
					}
				}
			}
		}
	}
}
