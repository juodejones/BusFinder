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
import java.util.*

class BusViewFragment : Fragment() {

    private val navArgs: BusViewFragmentArgs by navArgs()
    private lateinit var binding: FragmentBusViewBinding

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
        binding.busStopsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
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
//                        Log.d("MyInfoTag", "onViewCreated: ${bus?.stopList.toString()}")
                        val list: List<Map.Entry<String, Double>> = LinkedList<Map.Entry<String, Double>>(bus?.stopList!!.entries)
                        Collections.sort(list) { p0, p1 -> p0?.value!!.compareTo(p1?.value!!) }
                        val temp: HashMap<String, Double> = LinkedHashMap()
                        for ((key, value) in list) { temp[key] = value }
                        val recyclerViewAdapter =
                            BusesRvAdapter(requireContext(), false, null, temp, object : BusesRvAdapter.BusClickListener{
                                override fun onBusClick(bus: Bus) {
                                    //Nothing to do
                                }
                            })
                        binding.busStopsRv.adapter = recyclerViewAdapter
                    }
                }
            }
        }
    }
}