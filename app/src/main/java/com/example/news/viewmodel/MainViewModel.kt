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
	private val mNews = MutableLiveData<NewsResponse?>()
	val news: LiveData<NewsResponse?> = mNews

	private val mSearch = MutableLiveData<NewsResponse>()
	val search: LiveData<NewsResponse> = mSearch

	private val mNeedPBForSearch = MutableLiveData<Boolean>()
	val needPBForSearch: LiveData<Boolean> = mNeedPBForSearch

	fun getNews() = viewModelScope.launch {
		mNews.postValue(null)
		try {
			mNews.postValue(repo.getAllNewsFromApi())
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	fun searchNews(query: String) = viewModelScope.launch {
		mNeedPBForSearch.postValue(true)
		try {
			mSearch.postValue(repo.searchNews(query))
			mNeedPBForSearch.postValue(false)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}
