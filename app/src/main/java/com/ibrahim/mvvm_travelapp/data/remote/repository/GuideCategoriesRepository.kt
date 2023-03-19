package com.ibrahim.mvvm_travelapp.data.remote.repository

import com.ibrahim.mvvm_travelapp.data.remote.TravelApiService
import com.ibrahim.mvvm_travelapp.domain.model.guidecategories.GuideCategoriesItem
import retrofit2.Call
import javax.inject.Inject

class GuideCategoriesRepository@Inject constructor(private val travelApiService: TravelApiService) {

    fun getGuideCategories(): Call<List<GuideCategoriesItem>> {
        return travelApiService.getGuideCategories()
    }

}