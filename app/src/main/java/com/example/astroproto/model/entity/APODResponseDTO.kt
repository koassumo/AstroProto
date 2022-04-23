package com.example.astroproto.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class APODResponseDTO (

    @Expose
    val title: String? = null,

    @Expose
    val date: String? = null,

    @Expose
    val copyright: String? = null,

    @Expose
    val explanation: String? = null,

    @Expose
    val url: String? = null,

    @Expose
    val hdurl: String? = null,

    @Expose
    val mediaType: String? = null,

    @Expose
    val serviceVersion: String? = null,

): Parcelable