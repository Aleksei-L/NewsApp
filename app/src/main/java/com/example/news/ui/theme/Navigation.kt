package com.example.news.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.news.ui.theme.screen.DetailsScreen
import com.example.news.ui.theme.screen.FeedScreen
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
			FeedScreen(vm) {
				val encodedUrl = URLEncoder.encode(it.urlToImage, StandardCharsets.UTF_8.toString())
				navController.navigate("${Route.DETAILS.name}/${it.title}&${it.content}&$encodedUrl")
			}
		}
		composable("${Route.DETAILS.name}/{title}&{desc}&{urlToImage}", arguments = listOf(
			navArgument("title") {
				type = NavType.StringType
			},
			navArgument("desc") {
				type = NavType.StringType
			},
			navArgument("urlToImage") {
				type = NavType.StringType
			}
		)) {
			DetailsScreen(
				it.arguments?.getString("title") ?: "",
				it.arguments?.getString("desc") ?: "",
				it.arguments?.getString("urlToImage") ?: ""
			)
		}
		composable(Route.SEARCH.name) {
			/*val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
			SimpleScreen(
				title = "${Route.HomeChild.title} $screenNum",
				onNavigateToNextScreenClicked = { navigateToNextScreen(Route.HomeChild.route) }
			)*/
		}
	}
}

enum class Route {
	FEED,
	DETAILS,
	SEARCH
}