package com.example.astroproto.domain.remote.bycoroutines

import com.example.astroproto.entity.APODResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CorAPODApiService {
    @GET("planetary/apod")
    suspend fun getAPODFromDateToDate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): List<APODResponseDTO>
}