package com.jones.busfinder.data

data class Bus (
    var id: Integer? = null,
    var departureLoc: String? = null,
    var departureTime: String? = null,
    var reachTime: String? = null,
    var reachLocation: String? = null,
    var stopList: Map<String,String>? = null,
    var maxFare: Integer? = null,
    var isLadies: Boolean? = null,
        )