package com.ibrahim.mvvm_travelapp.presentation.trip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.ibrahim.mvvm_travelapp.R
import com.ibrahim.mvvm_travelapp.databinding.FragmentTripBinding
import com.ibrahim.mvvm_travelapp.databinding.TripDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripFragment :Fragment() {

    private lateinit var fragmentTripBinding: FragmentTripBinding

    lateinit var tripAdapter: TripAdapter
    lateinit var tripDialogAdapter: TripDialogAdapter
    private val tripViewModel: TripViewModel by viewModels()

    lateinit var tripDialogBinding: TripDialogBinding
    lateinit var bottomSheet: BottomSheetDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTripBinding = FragmentTripBinding.inflate(inflater, container, false)
        tripDialogBinding = TripDialogBinding.inflate(inflater, container, false)
        bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        bottomSheet.setContentView(tripDialogBinding.root)

        return fragmentTripBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()

    }

    private fun initClickListeners(){
        fragmentTripBinding.apply {
        tripTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        tripFab.visibility = View.VISIBLE
                        tripAdapter.setList(emptyList())
                    }
                    1 -> {
                        tripFab.visibility = View.INVISIBLE
                        initAdapter()
                        observeBookmarkList()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
            tripFab.setOnClickListener {
                bottomSheet.show()
                initDialogAdapter()
                fetchTravel()
                observeTravelList()
            }

            tripDialogBinding.dialogAdd.setOnClickListener{
               /* var sharedPreferences = activity?.getSharedPreferences("travel", Context.MODE_PRIVATE)
                var editor = sharedPreferences?.edit()
                var editorSecond = sharedPreferences?.edit()
                editor?.putString(tripDialogBinding.dialogTextinputCity.toString(),tripDialogBinding.dialogTextinputDate.toString())
                editorSecond?.putString(tripDialogBinding.dialogTextinputDay.toString(),tripDialogBinding.dialogTextinputPicture.toString())
                */
                bottomSheet.dismiss()
                findNavController().navigateUp()
            }

            tripDialogBinding.dialogStartingdateTextview.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) { resultKey, bundle->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        tripDialogBinding.dialogStartingdateTextview.text = "$date"

                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")

            }

            tripDialogBinding.dialogEndingdateTextview.setOnClickListener {

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) { resultKey, bundle->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        tripDialogBinding.dialogEndingdateTextview.text = "$date"

                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")

            }

        }
    }
    private fun observeBookmarkList() {
        tripViewModel.getTravelsByIsBookmark(true).observe(viewLifecycleOwner) {
            tripAdapter.setList(it)
        }
    }

    private fun initAdapter() {
        tripAdapter = TripAdapter()
        fragmentTripBinding.tripRecyclerviewPlan.adapter = tripAdapter
        //Adapterin Ekrandaki Görünümü
        val layoutManager = LinearLayoutManager(context)
        fragmentTripBinding.tripRecyclerviewPlan.layoutManager = layoutManager
    }

    private fun initDialogAdapter() {
        tripDialogAdapter = TripDialogAdapter()
        tripDialogBinding.dialogRecyclerview.adapter = tripDialogAdapter
        //Adapterin Ekrandaki Görünümü
        val dialogLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        tripDialogBinding.dialogRecyclerview.layoutManager = dialogLayoutManager
    }

    private fun fetchTravel() {
        tripDialogBinding.dialogRecyclerview.isVisible = false
        tripDialogBinding.dialogProgress.isVisible = true
        tripViewModel.getAllTravel()

    }

    private fun observeTravelList() {
        tripViewModel.getAllTravel().observe(viewLifecycleOwner) { travelList ->
            tripDialogAdapter.setList(travelList)
            tripDialogBinding.dialogRecyclerview.isVisible = true
            tripDialogBinding.dialogProgress.isVisible = false
        }
    }
}