package com.jones.busfinder

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jones.busfinder.data.Bus

class App: Application() {

    var busList: MutableLiveData<List<Bus?>> = MutableLiveData()
    var isDataAvailable: MutableLiveData<Boolean> = MutableLiveData(true)

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //Attaching listener to get data from firebase
        FirebaseDatabase.getInstance().getReference("Buses").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val buses = mutableListOf<Bus?>()
                    for (c in snapshot.children) {
                        buses.add(c.getValue(Bus::class.java))
                    }
                    Log.d("AppTag", "onDataChange: ${buses.size}")
                    busList.postValue(buses)
                    isDataAvailable.postValue(buses.isNotEmpty())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        var bus: Bus
//        var stopList : HashMap<String, Double>
//        var busDB = FirebaseDatabase.getInstance().getReference("Buses")
//        stopList = hashMapOf("Periar(Complex)" to 18.0,"Tnstc Ho" to 25.0,"Madura Coats" to 13.0,"Railway Station " to 16.0,"Madurai College" to 19.0,"Byepass" to 23.0,"Cross Road" to 10.0,"Melaponnaharam" to 11.0,"Vasantha Nagar" to 21.0,"Kalavasal" to 27.0,"Sethupathi School " to 14.0,"Arapalayam" to 8.0,)
//        bus = Bus("C1","Arapalayam","7:30","Kalavasal","8:10",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Theppakulam" to 12.0,"Pudhur" to 25.0,"Sethupathi" to 19.0,"Mattuthavani" to 0.0,"Surveyor Colony" to 27.0,"Annanagar" to 10.0,"Periar" to 16.0,"Thallakulam" to 24.0,"Poo Market" to 7.0,"Simmakal" to 21.0,"Railway Station" to 18.0,"KK Nagar" to 8.0,"Crime Branch" to 15.0,"Goripalayam" to 22.0,"Therkuvasal" to 13.0,)
//        bus = Bus("C4","Mattuthavani","7:00","Surveyor Colony ","8:00",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Samayanallur" to 0.0,"Periar Nilayam" to 15.0,"Simmakkal" to 17.0,"Goripalayam" to 19.0,"Pudhur" to 21.0,"Kadachanendhal" to 23.0,"Kallanthiri" to 25.0,"Alagarkovil" to 27.0,)
//        bus = Bus("44SA","Samayanallur","7:00","Alagarkovil","8:30",stopList,null,false)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Arapalayam" to 0.0,"Cross Road" to 13.0,"Simmakkal" to 15.0,"Goripalayam" to 17.0,"Mattuthavani" to 19.0,"Thallakulam" to 17.0,"Court" to 17.0,)
//        bus = Bus("77B","Arapalayam","8:00","Mattuthavani","9:00",stopList,null,false)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Mattuthavani" to 0.0,"Goripalayam" to 15.0,"Simmakkal" to 17.0,"Arapalayam" to 19.0,"Kaalavasal" to 21.0,"Kochadai" to 23.0,)
//        bus = Bus("77T","Mattuthavani","11:00","Kochadai","11:40",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Vilacheri" to 0.0,"Moolakarai" to 13.0,"Pasumalai" to 15.0,"Palaganatham" to 17.0,"Madura College" to 19.0,"Keelavasal" to 21.0,"Gh" to 23.0,"Therku Vasal" to 25.0,"Anna Nilayam" to 27.0,)
//        bus = Bus("9A","Vilacheri","14:20","Anna Nilayam","15:00",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Mattuthavani" to 0.0,"Anna Nilayam" to 12.0,"Sellur" to 14.0,"Paravai" to 16.0,"Samayanallur" to 18.0,"Thenur" to 20.0,"Sholavandhan" to 22.0,)
//        bus = Bus("93","Mattuthavani","10:30","Sholavandhan","11:20",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)
//        stopList = hashMapOf("Anupanadi" to 0.0,"Keela Vasal" to 14.0,"Therkuvasal" to 16.0,"Periar" to 18.0,"Arasaradi" to 19.0,"Arapalayam" to 21.0,"Koodal Nagar" to 23.0,"Anaiyur" to 25.0,)
//        bus = Bus("8","Anupanadi","16:00","Anaiyur","16:30",stopList,null,true)
//        busDB.child(bus.id!!).setValue(bus)

    }
}