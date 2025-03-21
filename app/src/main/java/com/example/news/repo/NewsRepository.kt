package com.example.news.repo

import com.example.news.data.NewsResponse
import com.example.news.repo.api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
	private val newsApi: NewsApi
) {
	suspend fun getAllNewsFromApi(): NewsResponse = withContext(Dispatchers.IO) {
		return@withContext newsApi.getAllNewsFromApi()
	}

	suspend fun searchNews(query: String): NewsResponse = withContext(Dispatchers.IO) {
		return@withContext newsApi.searchNews(query = query)
	}
}
