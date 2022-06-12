package com.example.astroproto.ui.apod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astroproto.ApiHolder
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import com.project.apod.usecases.APODUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.*


class ListAPODViewModel (): ViewModel() {

    private val apodUseCase: APODUseCase by lazy { APODUseCase() }
    val liveDataAPODVertical = MutableLiveData<List<APODResponseDTO>>()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val mRxSingle: Single<List<APODResponseDTO>> =
                    RetrofitRepoApi(ApiHolder().dataApi).getRepoListApi()
                apodUseCase.load()
                mRxSingle.subscribe({
                    liveDataAPODVertical.value = it.reversed()
                }, {
                })
                return@withContext
            }
        }
    }


//    private fun updateListFirst() {
//        liveDataAPODVertical.value = FakeRepository.getListAPODResponseDTO()
//    }

}