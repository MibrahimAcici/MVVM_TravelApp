package com.ibrahim.mvvm_travelapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.mvvm_travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.mvvm_travelapp.domain.model.guidecategories.GuideCategoriesItem
import com.ibrahim.mvvm_travelapp.domain.usecase.AllTravelUseCase
import com.ibrahim.mvvm_travelapp.domain.usecase.GuideCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel@Inject constructor(
    private val allTravelUseCase: AllTravelUseCase,
    private val guideCategoriesUseCase: GuideCategoriesUseCase
): ViewModel() {

    fun getAllTravel(): LiveData<List<AllTravelItem>> {
        allTravelUseCase.apply {
            getAllTravel()
            return allTravel
        }
    }

    fun getGuideCategories(): LiveData<List<GuideCategoriesItem>> {
        guideCategoriesUseCase.apply {
            getGuideCategories()
            return guideCategories
        }
    }
}