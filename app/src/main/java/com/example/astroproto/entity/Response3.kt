package com.example.astroproto.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response3(


    @Expose
    val flrID: String?,

//    @Expose
//    val instruments: List<Instrument>,
//    @Expose
//    val beginTime: String,
//    @Expose
//    val peakTime: String,
//    @Expose
//    val endTime: String,
//    @Expose
//    val classType: String,
//    @Expose
//    val sourceLocation: String,
//    @Expose
//    val activeRegionNum: Long?,
//    @Expose
//    val linkedEvents: List<LinkedEvent>?,
//    @Expose
//    val link: String
): Parcelable

@Parcelize
data class Instrument(
    @Expose
    val displayName: DisplayName
): Parcelable

@Parcelize
data class LinkedEvent(
    @Expose
    val activityID: String
): Parcelable

enum class DisplayName(val value: String) {
    GoesPExis1080("GOES-P: EXIS 1.0-8.0"),
    GoesSExis1080("GOES-S: EXIS 1.0-8.0");


}

