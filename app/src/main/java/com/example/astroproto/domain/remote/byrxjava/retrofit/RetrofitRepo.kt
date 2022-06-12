package com.example.astroproto.domain.remote.byrxjava.retrofit

import com.example.astroproto.entity.APODResponseDTO
import com.example.astroproto.entity.SolarResponseDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepo(val api: IDataApi) : IRetrofitRepo {
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

    override fun getRepoListApiDateToDate(startDate: String, endDate: String): Single<List<APODResponseDTO>> =
        api.getAPODFromDateToDate(startDate, endDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getRepoListSolarApi(): Single<List<SolarResponseDTO>> =
        api.getListSolarApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}