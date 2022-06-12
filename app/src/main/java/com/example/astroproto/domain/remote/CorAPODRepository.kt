package com.project.apod.domain.remote

import com.example.astroproto.model.entity.APODResponseDTO

class CorAPODRepository() {

    lateinit var corAPODApiService: CorAPODApiService

    suspend fun loadAsync(startDate: String, endDate: String): List<APODResponseDTO> {
        return corAPODApiService.getAPODFromDateToDate(startDate, endDate)

    }
}
