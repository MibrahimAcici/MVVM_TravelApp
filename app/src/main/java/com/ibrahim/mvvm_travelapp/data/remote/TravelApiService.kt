package com.ibrahim.mvvm_travelapp.data.remote

import com.ibrahim.mvvm_travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.mvvm_travelapp.domain.model.guidecategories.GuideCategoriesItem
import retrofit2.Call
import retrofit2.http.GET

interface TravelApiService {

    @GET("AllTravelList")
    fun getAllTravel() : Call<List<AllTravelItem>>

    @GET("GuideCategories")
    fun getGuideCategories() : Call<List<GuideCategoriesItem>>

}