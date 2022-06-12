package com.example.astroproto.domain.remote.byrxjava.retrofit

import com.example.astroproto.entity.APODResponseDTO
import com.example.astroproto.entity.SolarResponseDTO
import io.reactivex.rxjava3.core.Single

interface IRetrofitRepo {
    fun getRepoTodayApi(): Single<APODResponseDTO>
    fun getRepoOneDayApi(): Single<APODResponseDTO>
    fun getRepoListApi(): Single<List<APODResponseDTO>>
    fun getRepoListApiDateToDate(startDate: String, endDate: String): Single<List<APODResponseDTO>>
    fun getRepoListSolarApi(): Single<List<SolarResponseDTO>>

}