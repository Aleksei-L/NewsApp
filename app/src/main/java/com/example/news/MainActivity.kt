package com.example.news

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.news.repo.NetworkMonitor
import com.example.news.repo.NewsRepository
import com.example.news.repo.api.RetrofitObject.retrofitService
import com.example.news.ui.theme.NewsTheme
import com.example.news.ui.theme.navigation.NavigationHost
import com.example.news.viewmodel.MainViewModel
import com.example.news.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
	private lateinit var vm: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		vm = ViewModelProvider(
			this,
			MainViewModelFactory(NewsRepository(retrofitService))
		)[MainViewModel::class.java]

		val sharedPref = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE)

		enableEdgeToEdge()
		setContent { NewsTheme { NavigationHost(vm, sharedPref, this) } }

		registerNetworkMonitor()
	}

	override fun onStart() {
		super.onStart()
		vm.getNews()
	}

	private fun registerNetworkMonitor() {
		val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val netRequest = NetworkRequest.Builder()
			.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
			.addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
		val netHandler = NetworkMonitor(this)
		cm.registerNetworkCallback(netRequest, netHandler)
	}
}
