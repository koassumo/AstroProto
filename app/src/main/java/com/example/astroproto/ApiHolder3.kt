package com.example.astroproto

import com.example.astroproto.model.api.IDataApi3
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolder3 {

    // собираем полный api-запрос в объект dataApi:
    // здесь определяем базовый url + подключаем интерфейс IDataApi из модели

    val dataApi3: IDataApi3 by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataApi3::class.java)
    }

}