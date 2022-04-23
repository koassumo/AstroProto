package com.example.astroproto.model.api

import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.entity.Response3
import com.example.astroproto.model.entity.SolarResponseDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataApi3 {
    @GET( "/DONKI/FLR?api_key=DEMO_KEY")
    fun getApi3(): Single<List<Response3>>

}