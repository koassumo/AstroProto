package com.example.astroproto.ui

import com.example.astroproto.entity.SolarResponseDTO

sealed class AppState {
    // enum - ограниченные параметры
    // sealed class - ограниченное количество видов наследников

    object Loading: AppState() // object, т.к. всегда одинаковый
    data class SuccessSolar (val listSolarResponse: List<SolarResponseDTO>): AppState()
    data class Error (val error: Throwable): AppState()


}
