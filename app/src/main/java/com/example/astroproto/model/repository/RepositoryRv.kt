package com.example.astroproto.model.repository

import com.example.astroproto.model.entity.APODResponseDTO
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


    // заглушка для проверки отображения одного APOD

//    private val listAPODResponseDTO: List<APODResponseDTO> = listOf(
//
//        APODResponseDTO(
//            copyright = "Juan Carlos Casado",
//            date = "2022-04-16",
//            explanation = "Taken with a camera fixed to a tripod, many short exposures were aligned with the stars to unveil this beautiful, dark night sky. Captured near the rural village of Albany`a at the northeastern corner of Spain, the three stars of Orion's belt stretch across top center in the starry frame. Alnitak, the easternmost (left) of the belt stars is seen next to the more diffuse glow of the Flame Nebula and the dark notch of the famous Horsehead. Easily visible to the naked-eye The Great Nebula of Orion is below the belt stars. A mere 1,500 light-years distant, it is the closest large stellar nursery to our fair planet. Best seen in photographs, the broad and faint arc of Barnard's Loop seems to embrace Orion's brighter stars and nebulae though. In the northern spring the familiar northern winter constellation is setting. Near the western horizon toward lower right Orion's apparently bright blue supergiant Rigel just touches the branches of a pine tree." +
//                    "Taken with a camera fixed to a tripod, many short exposures were aligned with the stars to unveil this beautiful, dark night sky. Captured near the rural village of Albany`a at the northeastern corner of Spain, the three stars of Orion's belt stretch across top center in the starry frame. Alnitak, the easternmost (left) of the belt stars is seen next to the more diffuse glow of the Flame Nebula and the dark notch of the famous Horsehead. Easily visible to the naked-eye The Great Nebula of Orion is below the belt stars. A mere 1,500 light-years distant, it is the closest large stellar nursery to our fair planet. Best seen in photographs, the broad and faint arc of Barnard's Loop seems to embrace Orion's brighter stars and nebulae though. In the northern spring the familiar northern winter constellation is setting. Near the western horizon toward lower right Orion's apparently bright blue supergiant Rigel just touches the branches of a pine tree.",
//            url = "https://apod.nasa.gov/apod/image/2204/orionpines_2k.jpg,",
//            hdurl = "https://apod.nasa.gov/apod/image/2204/orionpines_1k.jpg",
//            mediaType = "image",
//            serviceVersion = "v1",
//            title = "Orion Pines Orion Pines Orion Pines",
//        ),
//
//        APODResponseDTO(
//            copyright = "Juan Carlos Casado",
//            date = "2022-04-15",
//            explanation = "Wind-sharpened rocks known as ventifacts, cover this broad sloping plain in the foot hills of Mount Sharp, Gale crater, Mars. Dubbed gator-back rocks their rugged, scaly appearance is captured in these digitally stitched Mastcam frames from the Curiosity rover on mission sol 3,415 (March 15, 2022). Driving over gator-back rocks before has resulted in damage to the rover's wheels, so Curiosity team members decided to turn around and take another path to continue the rover's climb. Curiosity has been on an ascent of Gale crater's central 5.5 kilometer high mountain since 2014. As it climbs, it's been able to study layers shaped by water on Mars billions of years ago.",
//            url = "https://apod.nasa.gov/apod/image/2204/PIA25175_1053.jpg",
//            hdurl = "",
//            mediaType = "image",
//            serviceVersion = "v1",
//            title = "The Gator-back Rocks of Mars",
//        ),
//
//        APODResponseDTO(
//            copyright = "Juan Carlos Casado",
//            date = "2022-04-15",
//            explanation = "Wind-sharpened rocks known as ventifacts, cover this broad sloping plain in the foot hills of Mount Sharp, Gale crater, Mars. Dubbed gator-back rocks their rugged, scaly appearance is captured in these digitally stitched Mastcam frames from the Curiosity rover on mission sol 3,415 (March 15, 2022). Driving over gator-back rocks before has resulted in damage to the rover's wheels, so Curiosity team members decided to turn around and take another path to continue the rover's climb. Curiosity has been on an ascent of Gale crater's central 5.5 kilometer high mountain since 2014. As it climbs, it's been able to study layers shaped by water on Mars billions of years ago.",
//            url = "https://apod.nasa.gov/apod/image/2204/PIA25175_1053.jpg",
//            hdurl = "",
//            mediaType = "image",
//            serviceVersion = "v1",
//            title = "The Gator-back Rocks of Mars",
//        ),
//
//        APODResponseDTO(
//            copyright = "Juan Carlos Casado",
//            date = "2022-04-15",
//            explanation = "Wind-sharpened rocks known as ventifacts, cover this broad sloping plain in the foot hills of Mount Sharp, Gale crater, Mars. Dubbed gator-back rocks their rugged, scaly appearance is captured in these digitally stitched Mastcam frames from the Curiosity rover on mission sol 3,415 (March 15, 2022). Driving over gator-back rocks before has resulted in damage to the rover's wheels, so Curiosity team members decided to turn around and take another path to continue the rover's climb. Curiosity has been on an ascent of Gale crater's central 5.5 kilometer high mountain since 2014. As it climbs, it's been able to study layers shaped by water on Mars billions of years ago.",
//            url = "https://apod.nasa.gov/apod/image/2204/PIA25175_1053.jpg",
//            hdurl = "",
//            mediaType = "image",
//            serviceVersion = "v1",
//            title = "The Gator-back Rocks of Mars",
//        ),
//
//
//
//
//    )

    //fun getListAPODResponseDTO() = listAPODResponseDTO


}