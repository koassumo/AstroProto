package com.example.astroproto.model.retrofit


import com.example.astroproto.model.entity.Response3
import io.reactivex.rxjava3.core.Single

interface IRetrofitApi3 {
    fun getRepo3(): Single<List<Response3>>
}