package com.ibrahim.mvvm_travelapp.domain.model.alltravel

import com.google.gson.annotations.SerializedName

data class AllTravel(

	@field:SerializedName("AllTravel")
	val allTravel: List<AllTravelItem?>? = null
)