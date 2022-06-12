package com.example.astroproto.domain.local.repository

import com.example.astroproto.entity.SolarResponseDTO

object RepositorySolar {
    private val listSolarResponse = mutableListOf(
        SolarResponseDTO ("2022-04-11T05:00Z", "C2.2"),
        SolarResponseDTO ("2022-04-15T11:07Z", "M2.2"),
        SolarResponseDTO ("2022-04-15T13:47Z", "M1.9"),
        SolarResponseDTO ("2022-04-17", "M1.0"),
        SolarResponseDTO ("2022-04-22T03:17Z", "X1.1"),
    )

    fun getListSolarResponse() = listSolarResponse

}