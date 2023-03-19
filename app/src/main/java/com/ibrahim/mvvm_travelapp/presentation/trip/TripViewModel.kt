package com.ibrahim.mvvm_travelapp.presentation.trip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ibrahim.mvvm_travelapp.data.db.Database
import com.ibrahim.mvvm_travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.mvvm_travelapp.domain.usecase.AllTravelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TripViewModel@Inject constructor(private val allTravelUseCase: AllTravelUseCase,application: Application): AndroidViewModel(application) {

        fun getTravelsByIsBookmark(isTrue:Boolean) = Database.getDatabase(getApplication()).traveldao().getTravelsByIsBookmark(isTrue)

        fun getAllTravel(): LiveData<List<AllTravelItem>> {
            allTravelUseCase.apply {
                getAllTravel()
                return allTravel
            }
        }
}