package com.example.astroproto.ui.apod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astroproto.domain.ApiHolder
import com.example.astroproto.entity.APODResponseDTO
import com.example.astroproto.domain.remote.byrxjava.retrofit.RetrofitRepo
import com.project.apod.usecases.APODUseCase
import io.reactivex.rxjava3.core.Single


class ListAPODViewModel (): ViewModel() {

    //private val apodUseCase: CorAPODUseCase by lazy { CorAPODUseCase() }
    val liveDataAPODVertical = MutableLiveData<List<APODResponseDTO>>()
    private val apodUseCase: APODUseCase by lazy { APODUseCase() }

    init {
        val mRxSingle: Single<List<APODResponseDTO>> = apodUseCase.load()

        mRxSingle.subscribe({
            liveDataAPODVertical.value = it.reversed()
        }, {
        })
    }



//    private fun updateListFirst() {
//        liveDataAPODVertical.value = FakeRepository.getListAPODResponseDTO()
//    }

}