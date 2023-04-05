package com.jones.busfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jones.busfinder.App
import com.jones.busfinder.data.Bus
import com.jones.busfinder.databinding.FragmentBusViewBinding
import com.jones.busfinder.ui.adapter.BusesRvAdapter

class BusViewFragment : Fragment(), BusesRvAdapter.BusClickListener {

    private val navArgs: BusViewFragmentArgs by navArgs()
    private lateinit var binding: FragmentBusViewBinding
    private lateinit var recyclerViewAdapter: BusesRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.busList.observe(this.viewLifecycleOwner) { busList ->
            if (busList?.isNotEmpty()!!) {
                busList.forEach { bus ->
                    if (bus?.id?.lowercase().equals(navArgs.busID?.lowercase())!!) {
                        binding.busRouteIdTv.text =
                            buildString { append("Bus Route ID : " + bus?.id) }
                        binding.busSourceTv.text = buildString {
                            append("Bus Departure from " + bus?.departureLoc)
                            append(" at " + bus?.departureTime)
                        }
                        binding.busDestinTv.text = buildString {
                            append("Bus Reaches " + bus?.reachLocation)
                            append(" by " + bus?.reachTime)
                        }
                        recyclerViewAdapter =
                            BusesRvAdapter(requireContext(), false, null, bus?.stopList, this)
                        binding.busStopsRv.layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                        binding.busStopsRv.adapter = recyclerViewAdapter
                    }
                }
            }
        }
    }

    override fun onBusClick(bus: Bus) {
        //Nothing to do here
    }
}