package com.jones.busfinder.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jones.busfinder.App
import com.jones.busfinder.data.Bus

class MainViewModel(
    private val app: Fragment,
) : AndroidViewModel(app.requireActivity().application) {

    private var busList: MutableLiveData<List<Bus?>> = MutableLiveData()

    fun getBySD(source: String, destin: String): MutableLiveData<List<Bus?>>? {
        App.instance.busList.observe(app.viewLifecycleOwner) { buses ->
            busList.postValue(buses.filter {
                it?.stopList?.containsKey(source)!! &&
                        it.stopList?.containsKey(destin)!!
            }.toList())
        }
        Log.d("MainViewModel", "getBySD: ${busList.value?.size}")
        return busList
    }

    fun getById(id: String): MutableLiveData<List<Bus?>>? {
        App.instance.busList.observe(app.viewLifecycleOwner) { buses ->
            busList.postValue(buses.filter { it?.id?.equals(id)!! }.toList())
        }
        Log.d("MainViewModel", "getByID: ${busList.value?.size}")
        return busList
    }

    fun getByStop(stop: String): MutableLiveData<List<Bus?>>? {
        App.instance.busList.observe(app.viewLifecycleOwner) { buses ->
            busList.postValue(buses.filter { it?.stopList?.containsKey(stop)!! }.toList())
        }
        Log.d("MainViewModel", "getByStop: ${busList.value?.size}")
        return busList
    }

}