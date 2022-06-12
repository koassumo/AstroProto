package com.example.astroproto.ui

import android.view.View
import com.example.astroproto.entity.APODResponseDTO

interface IMyOnClickListenerAPOD {
    fun onMyClicked (apodResponseDTO: APODResponseDTO)
}