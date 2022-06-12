package com.project.apod.domain.remote

import com.example.astroproto.ApiHolder
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi

class APODRepository() {

    lateinit var apodApiService: APODApiService

    suspend fun loadAsync(startDate: String, endDate: String): List<APODResponseDTO> {
        //apodApiService.getAPODFromDateToDate(startDate, endDate)

        //return RetrofitRepoApi(ApiHolder().dataApi).getRepoListApi()
        return emptyList()
    }
}
