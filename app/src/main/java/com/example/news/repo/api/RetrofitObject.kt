package com.example.news.repo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
	private var retrofit: Retrofit = Retrofit.Builder()
		.baseUrl("https://newsapi.org/")
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	var retrofitService: NewsApi = retrofit.create(NewsApi::class.java)
}
