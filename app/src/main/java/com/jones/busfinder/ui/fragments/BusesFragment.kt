package com.jones.busfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jones.busfinder.R
import com.jones.busfinder.data.Bus
import com.jones.busfinder.databinding.FragmentBusesBinding
import com.jones.busfinder.ui.adapter.BusesRvAdapter
import com.jones.busfinder.ui.viewmodel.MainViewModel
import com.jones.busfinder.ui.viewmodel.MainViewModelFactory

class BusesFragment : Fragment(), BusesRvAdapter.BusClickListener {

    private lateinit var binding: FragmentBusesBinding
    private val navArgs: BusesFragmentArgs by navArgs()
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var recyclerViewAdapter: BusesRvAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusesBinding.inflate(layoutInflater, container, false)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModelFactory = MainViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val searchType = navArgs.searchType
//        Log.d("BusesFragment", "onCreateView: ${navArgs.searchType} + ${navArgs.input1}")

        binding.busesRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        if (searchType == 0) {
            viewModel.getById(navArgs.input1!!)!!.observe(this.viewLifecycleOwner) {
                recyclerViewAdapter = BusesRvAdapter(requireContext(), true, it, null, this)
                binding.busesRv.adapter = recyclerViewAdapter
                binding.notFoundTv.visibility = View.GONE
            }
        } else if (searchType == 1) {
            viewModel.getBySD(navArgs.input1!!, navArgs.input2!!)!!
                .observe(this.viewLifecycleOwner) {
                    recyclerViewAdapter = BusesRvAdapter(requireContext(), true, it, null, this)
                    binding.busesRv.adapter = recyclerViewAdapter
                    binding.notFoundTv.visibility = View.GONE
                }
        } else if (searchType == 2) {
            viewModel.getByStop(navArgs.input1!!)!!.observe(this.viewLifecycleOwner) {
                recyclerViewAdapter = BusesRvAdapter(requireContext(), true, it, null, this)
                binding.busesRv.adapter = recyclerViewAdapter
                binding.notFoundTv.visibility = View.GONE
            }
        } else {
            binding.busesRv.visibility = View.GONE
            binding.notFoundTv.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onBusClick(bus: Bus) {
        val action = BusesFragmentDirections.actionBusesFragmentToBusViewFragment(bus.id)
        navController.navigate(action)
    }
}