package com.example.astroproto.ui.apod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astroproto.model.repository.RepositoryRv
import com.example.astroproto.model.entity.APODResponseDTO


class ListAPODViewModel : ViewModel() {

    val liveDataAPODVertical = MutableLiveData<List<APODResponseDTO>>()

    init {
        // updateListFirst()
    }

    private fun updateListFirst() {
        liveDataAPODVertical.value = RepositoryRv.getListAPODResponseDTO()
    }

}