package com.example.astroproto.domain

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.core.domain.CorBaseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CorApiHolder {
    // собираем полный api-запрос в объект dataApi:
    // здесь определяем базовый url + подключаем интерфейс BuAPODApiService из domain.remote

    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(OkHttpClient.Builder().apply {
            addInterceptor(CorBaseInterceptor.interceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build())
        .build()

//    val dataApi: IDataApi by lazy {
//        val gson = GsonBuilder()
//            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .excludeFieldsWithoutExposeAnnotation()
//            .create()
//
//        Retrofit.Builder()
//            .baseUrl("https://api.nasa.gov")
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(IDataApi::class.java)
//    }
}