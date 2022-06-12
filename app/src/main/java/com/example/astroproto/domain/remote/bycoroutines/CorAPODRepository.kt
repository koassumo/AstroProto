package com.example.astroproto.domain.remote.bycoroutines

import com.example.astroproto.entity.APODResponseDTO

class CorAPODRepository() {

    lateinit var corAPODApiService: CorAPODApiService

    suspend fun loadAsync(startDate: String, endDate: String): List<APODResponseDTO> {
        return corAPODApiService.getAPODFromDateToDate(startDate, endDate)

    }
}
