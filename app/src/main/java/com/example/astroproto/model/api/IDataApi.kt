package com.example.astroproto.model.api

import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.entity.SolarResponseDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataApi {
    @GET( "/planetary/apod?api_key=DEMO_KEY")
    fun getTodayApi(): Single<APODResponseDTO>

    @GET( "/planetary/apod?api_key=DEMO_KEY&date=2022-03-29")
    fun getOneDayApi(): Single<APODResponseDTO>

    @GET( "/planetary/apod?api_key=DEMO_KEY&start_date=2022-03-29&end_date=2022-04-07")
    fun getListApi(): Single<List<APODResponseDTO>>

    @GET( "/DONKI/FLR?api_key=DEMO_KEY")
    fun getListSolarApi(): Single<List<SolarResponseDTO>>


}