package com.project.apod.usecases

import com.example.astroproto.entity.APODResponseDTO
import com.example.astroproto.domain.ApiHolder
import com.example.astroproto.domain.remote.byrxjava.retrofit.RetrofitRepo
import com.project.core.domain.CalendarRepository
import io.reactivex.rxjava3.core.Single

class APODUseCase() {

    private val calendarRepository: CalendarRepository by lazy { CalendarRepository() }
    private val retrofitRepo: RetrofitRepo by lazy { RetrofitRepo(ApiHolder().dataApi) }

    fun load(): Single<List<APODResponseDTO>> {
        calendarRepository.refreshDates(CalendarRepository.RangeFlag.ONE_MONTH)
//        println("___________${calendarRepository.startDate}")
//        println("___________${calendarRepository.endDate}")
        return retrofitRepo.getRepoListApiDateToDate(calendarRepository.startDate, calendarRepository.endDate)
        //remoteRepository.loadAsync(calendarRepository.startDate, calendarRepository.endDate)
    }

    suspend fun reload(): List<APODResponseDTO> {
        calendarRepository.refreshDates(CalendarRepository.RangeFlag.ONE_MONTH)
        //return remoteRepository.loadAsync(calendarRepository.startDate, calendarRepository.endDate)
        return emptyList()

    }

}