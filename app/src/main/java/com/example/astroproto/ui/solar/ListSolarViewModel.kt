package com.example.astroproto.ui.solar

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astroproto.domain.local.repository.RepositorySolar
import com.example.astroproto.entity.SolarResponseDTO
import java.util.*

class ListSolarViewModel : ViewModel() {

    val liveDataSolarVertical = MutableLiveData<List<SolarResponseDTO>>()
    init {updateListFirst()}
    private fun updateListFirst() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        // создаем массив 1 (listForRV), заполняем датами за 30 дней, класс солнечной активности "B"
        val listForRV = mutableListOf<SolarResponseDTO>()
        repeat(30) {
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            listForRV.add(SolarResponseDTO(sdf.format(calendar.time), "B"))
        }

        // создаем массив 2 (listSolarResponse) с данными из API (сейчас здесь заглушка с датами
        // из репозитория)
        val listSolarResponse = RepositorySolar.getListSolarResponse().onEach {
            it.beginTime = it.beginTime?.take(10)
            // берем только 10 первых символов, т.к. они определяют дату в формате "yyyy-MM-dd"
        }

        // В массиве 1 берем каждый элемент (из 30 шт.) и проверяем есть ли такой элемент в
        // массиве 2 (сравниваем по дате). Если есть, то заменяем элемент массива 1 на
        // элемент массива 2
        listSolarResponse.forEach { itemResponse ->
            val seekTime = itemResponse.beginTime
            listForRV.forEach { itemRVData ->
                if (itemRVData.beginTime.equals(seekTime))
                    itemRVData.classType =itemResponse.classType
            }
        }

        //при необходимости можно добавить какие-нибудь headers на неделю или на месяц
        listForRV.add(0, SolarResponseDTO("April", "header"))
        listForRV.add(28, SolarResponseDTO("March", "header"))

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

