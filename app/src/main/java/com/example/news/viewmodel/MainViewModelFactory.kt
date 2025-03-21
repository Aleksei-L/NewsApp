package com.example.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.news.repo.NewsRepository
import kotlin.reflect.KClass


class MainViewModelFactory(
	private val repo: NewsRepository
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(repo) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
