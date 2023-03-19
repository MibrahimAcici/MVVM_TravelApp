package com.ibrahim.mvvm_travelapp.data.remote.repository

import com.ibrahim.mvvm_travelapp.data.remote.TravelApiService
import com.ibrahim.mvvm_travelapp.domain.model.alltravel.AllTravelItem
import retrofit2.Call
import javax.inject.Inject

class AllTravelRepository @Inject constructor(private val travelApiService: TravelApiService) {

    fun getAllTravel(): Call<List<AllTravelItem>> {
        return travelApiService.getAllTravel()
    }

}
