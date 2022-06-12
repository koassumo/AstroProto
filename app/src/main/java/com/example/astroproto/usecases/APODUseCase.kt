package com.project.apod.usecases

import com.example.astroproto.model.entity.APODResponseDTO
import com.project.apod.domain.remote.APODRepository
import com.project.core.domain.CalendarRepository

class APODUseCase() {

    private val calendarRepository: CalendarRepository by lazy { CalendarRepository() }
    private val remoteRepository: APODRepository by lazy { APODRepository() }

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