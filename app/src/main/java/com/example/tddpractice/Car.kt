package com.example.tddpractice

class Car(var fule: Double, val engine: Engine) {
    fun turnOn() {
        fule -= 0.5
        engine.turnOn()
    }
}
