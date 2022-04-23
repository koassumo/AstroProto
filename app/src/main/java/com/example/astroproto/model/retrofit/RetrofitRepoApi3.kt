package com.example.astroproto.model.retrofit


import com.example.astroproto.model.api.IDataApi3
import com.example.astroproto.model.entity.Response3
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepoApi3(val api: IDataApi3) : IRetrofitApi3 {

    override fun getRepo3(): Single<List<Response3>> =
        api.getApi3()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}