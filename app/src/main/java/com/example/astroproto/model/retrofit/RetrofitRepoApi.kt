package com.example.astroproto.model.retrofit

import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.api.IDataApi
import com.example.astroproto.model.entity.SolarResponseDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepoApi(val api: IDataApi) : IRetrofitApi {
    override fun getRepoTodayApi(): Single<APODResponseDTO> =
        api.getTodayApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getRepoOneDayApi(): Single<APODResponseDTO> =
        api.getOneDayApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getRepoListApi(): Single<List<APODResponseDTO>> =
        api.getListApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getRepoListSolarApi(): Single<List<SolarResponseDTO>> =
        api.getListSolarApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())



}