package com.example.astroproto.model.retrofit

import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.entity.SolarResponseDTO
import io.reactivex.rxjava3.core.Single

interface IRetrofitApi {
    fun getRepoTodayApi(): Single<APODResponseDTO>
    fun getRepoOneDayApi(): Single<APODResponseDTO>
    fun getRepoListApi(): Single<List<APODResponseDTO>>
    fun getRepoListSolarApi(): Single<List<SolarResponseDTO>>

}