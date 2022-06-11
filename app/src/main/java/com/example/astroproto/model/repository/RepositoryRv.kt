package com.example.astroproto.model.repository

import com.example.astroproto.ui.main.ItemRvCommon

object RepositoryRv {
    private val listItemRvAPOD: List<ItemRvCommon> = listOf(
        ItemRvCommon("Today", "rv_apod_today"),
        ItemRvCommon("Before", "rv_apod_before"),
    )

    private val listItemRvSolarFlare: List<ItemRvCommon> = listOf(
        ItemRvCommon("Today", "rv_solar_today"),
        ItemRvCommon("Before", "rv_solar_before"),
        ItemRvCommon("Forecast", "rv_solar_forecast"),
    )

    private val listItemRvGeoStorm: List<ItemRvCommon> = listOf(
        ItemRvCommon("Today", "rv_geo_today"),
        ItemRvCommon("Before", "rv_geo_before"),
        ItemRvCommon("Forecast", "rv_geo_forecast"),
    )

    private val listItemRvEpic: List<ItemRvCommon> = listOf(
        ItemRvCommon("Today", "rv_epic_today"),
        ItemRvCommon("Before", "rv_epic_before"),
    )

    private val listItemRvMars: List<ItemRvCommon> = listOf(
        ItemRvCommon("Curiosity", "rv_mars_curiosity"),
        ItemRvCommon("Spirit", "rv_mars_spirit"),
        ItemRvCommon("Opportunity", "rv_mars_opportunity"),
    )

    fun getListRvAPOD() = listItemRvAPOD
    fun getListRvSolarFlare() = listItemRvSolarFlare
    fun getListRvGeoStorm() = listItemRvGeoStorm
    fun getListRvEpic() = listItemRvEpic
    fun getListRvMars() = listItemRvMars

}