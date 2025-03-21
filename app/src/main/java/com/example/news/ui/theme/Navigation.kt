package com.example.news.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news.ui.theme.screen.FeedScreen
import com.example.news.viewmodel.MainViewModel

@Composable
fun NavigationHost(
	vm: MainViewModel,
	navController: NavHostController = rememberNavController()
) {
	NavHost(navController, startDestination = Route.FEED.name) {
		composable(Route.FEED.name) {
			FeedScreen(vm)
		}
		composable(Route.DETAILS.name) {

		}
		composable(Route.SEARCH.name) { backStackEntry ->
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