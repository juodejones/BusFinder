package com.jones.busfinder.data

data class Bus (
    var id: String? = null,
    var departureLoc: String? = null,
    var departureTime: String? = null,
    var reachLocation: String? = null,
    var reachTime: String? = null,
    var stopList: HashMap<String,Double>? = null,
    var maxFare: Int? = null,
    var isLadies: Boolean? = null,
        )