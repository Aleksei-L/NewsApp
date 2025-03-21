package com.example.news.ui.theme.screen

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun FeedScreen(
	vm: MainViewModel,
	navController: NavController,
	sf: SharedPreferences,
	context: Context,
	onCardClick: (Article) -> Unit
) {
	val news by vm.news.observeAsState()
	var isFirstLaunch by remember {
		mutableStateOf(sf.getBoolean("first_launch", true))
	}

	if (isFirstLaunch) {
		NewsTheme {
			Scaffold(
				modifier = Modifier.fillMaxSize()
			) { innerPadding ->
				var login by remember { mutableStateOf("") }
				var password by remember { mutableStateOf("") }
				Column(
					modifier = Modifier
						.padding(innerPadding)
						.fillMaxSize(),
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally

				) {
					TextField(login, { login = it })
					TextField(password, { password = it })
					Button(onClick = {
						if (checkLogin(login)) {
							with(sf.edit()) {
								putBoolean("first_launch", false)
								apply()
							}

							isFirstLaunch = false
						} else {
							Toast.makeText(
								context,
								"Login must not contain the number 0",
								Toast.LENGTH_SHORT
							).show()
						}
					}) {
						Text("Log in")
					}
				}
			}
		}
	} else {
		NewsTheme {
			Scaffold(
				modifier = Modifier.fillMaxSize(),
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
								onClick = {
									navController.navigate(item.title)
								}
							)
						}
					}
				}
			) { innerPadding ->
				Column(modifier = Modifier.padding(innerPadding)) {
					if (news == null) {
						LinearProgressIndicator(
							modifier = Modifier.fillMaxWidth()
						)
					}
					LazyColumn(
						verticalArrangement = Arrangement.spacedBy(20.dp)
					) {
						items(news?.articles ?: return@LazyColumn) { item ->
							NewsCard(item.title, item.description ?: "", item.urlToImage) {
								onCardClick(item)
							}
						}
					}
				}
			}
		}
	}
}

fun checkLogin(login: String): Boolean {
	return !login.contains('0')
}
