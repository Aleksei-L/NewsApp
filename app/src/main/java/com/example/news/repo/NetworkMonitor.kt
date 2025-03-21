package com.example.news.repo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast

class NetworkMonitor(
	private val context: Context
) : ConnectivityManager.NetworkCallback() {

	override fun onLost(network: Network) {
		Toast.makeText(context, "Internet connection lost", Toast.LENGTH_SHORT).show()
	}

	override fun onUnavailable() {
		Toast.makeText(context, "Internet connection lost", Toast.LENGTH_SHORT).show()
	}

	override fun onAvailable(network: Network) {
		Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show()
	}
}
