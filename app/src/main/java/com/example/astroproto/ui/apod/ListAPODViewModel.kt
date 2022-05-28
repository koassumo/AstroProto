package com.example.astroproto.ui.apod

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astroproto.ApiHolder
import com.example.astroproto.model.repository.RepositoryRv
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.*


class ListAPODViewModel : ViewModel() {

    val liveDataAPODVertical = MutableLiveData<List<APODResponseDTO>>()

    init {
//        updateListFirst()


        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                val anySingle: Single<List<APODResponseDTO>> =
                    RetrofitRepoApi(ApiHolder().dataApi).getRepoListApi()
                anySingle.subscribe({
                    println("$it---------------------------===1===1====1========--------------------")
                    println("--------------------------------====1===1===1========--------------------")

                    liveDataAPODVertical.value = it

                }, {
                    println("onError: ${it.message}-----------err-------------------*----*-----------")
                })
                return@withContext
            }

            Log.d("mTAG", "_____________$result")
        }
    }


//    private fun updateListFirst() {
//        liveDataAPODVertical.value = RepositoryRv.getListAPODResponseDTO()
//    }

}