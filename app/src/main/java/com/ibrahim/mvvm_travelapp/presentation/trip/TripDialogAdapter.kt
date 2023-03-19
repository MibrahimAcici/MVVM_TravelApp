package com.ibrahim.mvvm_travelapp.presentation.trip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.mvvm_travelapp.databinding.TripDialogItemBinding
import com.ibrahim.mvvm_travelapp.domain.model.alltravel.AllTravelItem

class TripDialogAdapter() : RecyclerView.Adapter<TripDialogAdapter.TripDialogVH>() {

    private var allTravelItemList = mutableListOf<AllTravelItem?>()

    fun setList(newList: List<AllTravelItem?>?) {
        this.allTravelItemList.clear()
        this.allTravelItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class TripDialogVH(val binding: TripDialogItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripDialogVH {
        return TripDialogVH(TripDialogItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TripDialogVH, position: Int) {
        val travel=allTravelItemList[position]

        Glide.with(holder.binding.tripDialogItemImageview.context)
            .load(travel?.images?.get(0)?.url.toString())
            .into(holder.binding.tripDialogItemImageview)

        holder.binding.tripDialogItemTextviewCity.text = travel?.city.toString()
        holder.binding.tripDialogItemTextviewCountry.text = travel?.country.toString()

        holder.binding.root.setOnClickListener {
            if (holder.binding.tripDialogItemPickImageview.visibility == View.INVISIBLE){
                holder.binding.tripDialogItemPickImageview.visibility = View.VISIBLE
            }else{
                holder.binding.tripDialogItemPickImageview.visibility = View.INVISIBLE
            }
            val imageUrl = travel?.images?.get(0)?.url.toString()
            /*val action= HomeFragmentDirections.actionHomeFragmentToDetailFragment(travel,imageUrl)
            Navigation.findNavController(it).navigate(action)*/
        }

    }



    override fun getItemCount(): Int = allTravelItemList.size

}