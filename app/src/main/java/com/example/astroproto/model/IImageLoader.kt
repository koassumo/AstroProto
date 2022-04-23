package com.example.astroproto.model

interface IImageLoader <T>{
    fun loadInto (url: String, container: T)
}