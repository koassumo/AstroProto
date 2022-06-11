package com.example.astroproto.model.api

import androidx.lifecycle.MutableLiveData
import com.example.astroproto.BuildConfig
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.entity.SolarResponseDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IDataApi {


    //@Headers("api_key=" + BuildConfig.NASA_API_KEY)

    @GET( "/planetary/apod?api_key=DEMO_KEY")
    fun getTodayApi(): Single<APODResponseDTO>

    @GET( "/planetary/apod?api_key=DEMO_KEY&date=2022-03-29")
    fun getOneDayApi(): Single<APODResponseDTO>

    @GET( "/planetary/apod?start_date=2022-04-28&end_date=2022-05-12" + "&api_key=" + BuildConfig.NASA_API_KEY)
    fun getListApi(): Single<List<APODResponseDTO>>

    @GET( "/planetary/apod?start_date=2022-01-28&end_date=2022-04-12" + "&api_key=" + BuildConfig.NASA_API_KEY)
    fun getListApiSecond(): MutableLiveData<List<APODResponseDTO>>

    @GET( "/DONKI/FLR?api_key=DEMO_KEY")
    fun getListSolarApi(): Single<List<SolarResponseDTO>>


}