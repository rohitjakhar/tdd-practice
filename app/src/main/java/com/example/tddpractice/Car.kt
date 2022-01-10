package com.example.tddpractice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(var fule: Double, val engine: Engine) {
    fun turnOn() {
        fule -= 0.5
        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn()
        }

    }
}
