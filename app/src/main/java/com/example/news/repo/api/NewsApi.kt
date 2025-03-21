package com.example.news.repo.api

import com.example.news.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val API_KEY = "552e1003693f446ca4ad56e0599feab3"

interface NewsApi {
	@GET("v2/top-headlines")
	suspend fun getAllNewsFromApi(
		@Query("apiKey") key: String = API_KEY,
		@Query("country") country: String = "us",
		@Header("User-Agent") header: String = "Mozilla/5.0"
	): NewsResponse
}