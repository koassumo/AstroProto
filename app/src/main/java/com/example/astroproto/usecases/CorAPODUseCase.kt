package com.project.apod.usecases

import com.example.astroproto.entity.APODResponseDTO
import com.example.astroproto.domain.remote.bycoroutines.CorAPODRepository
import com.project.core.domain.CalendarRepository

class CorAPODUseCase() {

    private val calendarRepository: CalendarRepository by lazy { CalendarRepository() }
    private val remoteRepository: CorAPODRepository by lazy { CorAPODRepository() }

    suspend fun load(): List<APODResponseDTO> {
        calendarRepository.refreshDates(CalendarRepository.RangeFlag.ONE_MONTH)
//        println("___________${calendarRepository.startDate}")
//        println("___________${calendarRepository.endDate}")
        return remoteRepository.loadAsync(calendarRepository.startDate, calendarRepository.endDate)
//      return emptyList()
    }

    suspend fun reload(): List<APODResponseDTO> {
        calendarRepository.refreshDates(CalendarRepository.RangeFlag.ONE_MONTH)
        //return remoteRepository.loadAsync(calendarRepository.startDate, calendarRepository.endDate)
        return emptyList()

    }

}