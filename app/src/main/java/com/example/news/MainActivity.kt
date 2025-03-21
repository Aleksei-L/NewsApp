package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.news.repo.NewsRepository
import com.example.news.repo.api.RetrofitObject.retrofitService
import com.example.news.ui.theme.navigation.NavigationHost
import com.example.news.ui.theme.NewsTheme
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

		enableEdgeToEdge()
		setContent { NewsTheme { NavigationHost(vm) } }
	}

	override fun onStart() {
		super.onStart()
		vm.getNews()
	}
}
