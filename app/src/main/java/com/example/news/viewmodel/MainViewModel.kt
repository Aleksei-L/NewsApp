package com.example.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.NewsResponse
import com.example.news.repo.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(
	private val repo: NewsRepository
) : ViewModel() {
	private val mNews = MutableLiveData<NewsResponse>()
	val news: LiveData<NewsResponse> = mNews

	fun getNews() = viewModelScope.launch {
		try {
			mNews.postValue(repo.getAllNewsFromApi())
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

}
