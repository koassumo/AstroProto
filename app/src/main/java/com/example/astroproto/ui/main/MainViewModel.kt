package com.example.astroproto.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astroproto.model.repository.RepositoryRv

class MainViewModel : ViewModel() {
    //   <<<<<<<<<<<<<<<<<<<[[   1.
    val liveDataAPOD = MutableLiveData<List<ItemRvCommon>>()
    val liveDataSolar = MutableLiveData<List<ItemRvCommon>>()
    val liveDataGeo = MutableLiveData<List<ItemRvCommon>>()
    val liveDataEpic = MutableLiveData<List<ItemRvCommon>>()
    val liveDataMars = MutableLiveData<List<ItemRvCommon>>()

    init {
        updateListFirst()
    }

    private fun updateListFirst() {
        //    <<<<<<<<<<<<<<<<<<<[[   3.
        liveDataAPOD.value = RepositoryRv.getListRvAPOD()
        liveDataSolar.value = RepositoryRv.getListRvSolarFlare()
        liveDataGeo.value = RepositoryRv.getListRvGeoStorm()
        liveDataEpic.value = RepositoryRv.getListRvEpic()
        liveDataMars.value = RepositoryRv.getListRvMars()
    }



}