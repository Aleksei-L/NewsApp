package com.example.news.ui.theme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.news.ui.theme.screen.DetailsScreen
import com.example.news.ui.theme.screen.FeedScreen
import com.example.news.ui.theme.screen.SearchScreen
import com.example.news.viewmodel.MainViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigationHost(
	vm: MainViewModel,
	navController: NavHostController = rememberNavController()
) {
	NavHost(navController, startDestination = Route.FEED.name) {
		composable(Route.FEED.name) {
			FeedScreen(vm, navController) {
				val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
				navController.navigate("${Route.DETAILS.name}/$encodedUrl")
			}
		}
		composable(
			"${Route.DETAILS.name}/{url}", arguments = listOf(
				navArgument("url") {
				type = NavType.StringType
			}
		)) {
			DetailsScreen(
				it.arguments?.getString("url")
			)
		}
		composable(Route.SEARCH.name) {
			SearchScreen(vm, navController){
				val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
				navController.navigate("${Route.DETAILS.name}/$encodedUrl")
			}
		}
	}
}

enum class Route {
	FEED,
	DETAILS,
	SEARCH
}

enum class Tabs(val title: String, val icon: ImageVector) {
	HOME("Feed", Icons.Rounded.Home),
	SEARCH("Search", Icons.Rounded.Search)
}
