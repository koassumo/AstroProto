package com.example.astroproto.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SolarResponseDTO(

    @Expose
    var beginTime: String? = null,

    @Expose
    var classType: String? = null,

): Parcelable
