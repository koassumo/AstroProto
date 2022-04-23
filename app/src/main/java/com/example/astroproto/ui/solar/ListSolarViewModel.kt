package com.example.astroproto.ui.solar

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astroproto.model.repository.RepositorySolar
import com.example.astroproto.model.entity.SolarResponseDTO
import java.util.*

class ListSolarViewModel : ViewModel() {

    val liveDataSolarVertical = MutableLiveData<List<SolarResponseDTO>>()

    init {
        updateListFirst()
    }

    private fun updateListFirst() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        // создаем лист, дополняем датами
        var listForRV = mutableListOf<SolarResponseDTO>()
        repeat(55) {
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            listForRV.add(SolarResponseDTO(sdf.format(calendar.time), "B"))
        }

        // создаем вспомогательный массив
        // var listSolarResponse = mutableListOf<SolarResponseDTO>()
        var listSolarResponse = RepositorySolar.getListSolarResponse().onEach {
            it.beginTime = it.beginTime?.take(10)
        }
        println(listSolarResponse.size)





        // заменяем в массивеRV нужными данными из API
        listSolarResponse.forEach { itemResponse ->
            val seekTime = itemResponse.beginTime
            listForRV.forEach { itemRVData ->
                if (itemRVData.beginTime.equals(seekTime)) itemRVData.classType =
                    itemResponse.classType
            }
        }

        listForRV.add(0, SolarResponseDTO("This is header0", "header"))
        listForRV.add(5, SolarResponseDTO("This is header1", "header"))
        listForRV.add(10, SolarResponseDTO("This is header2", "header"))

        liveDataSolarVertical.value = listForRV
    }
}


//        val mySingle2: Single<List<SolarResponseDTO>> = RetrofitRepoApi(ApiHolder().dataApi).getRepoListSolarApi()
//        mySingle2.subscribe({
//            println("$it-----------------======single2============--------------------")
//            println("-----------------==single2================--------------------")
//            liveDataSolarVertical.value = it
//            listForRV = it as MutableList<SolarResponseDTO>
//
//        }, {
//            println("onError: ${it.message}-----------*--------*-------------*----*-----------")
//        })
//


//val listSolarResponse = RepositorySolar.getListSolarResponse().apply {
//    this.forEach {
//        it.beginTime = it.beginTime?.take(10)?.padEnd(14)
//    }
//}


//        val listSolarResponse0 = MutableList(48) {SolarResponse ("lsasfdsfaddakfj", "laj")}
//        var randomClassType = "B"
//        when ((0..5).random()) {
//            0 -> randomClassType = "X"
//            1 -> randomClassType = "M"
//            2 -> randomClassType = "C"
//            3 -> randomClassType = "C"
//        }

