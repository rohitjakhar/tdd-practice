package com.example.tddpractice

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
    var temperature: Int = 15,
    var isTurnedOn: Boolean = false
) {

    suspend fun turnOn() {
        isTurnedOn = true
        delay(5000)
        temperature = 95

        Log.d("test", "Engine has turned on")
    }
}
