package com.example.tddpractice

class Engine(
    val cc: Int,
    val horsePower: Int,
    var temperature: Int,
    var isTurnOn: Boolean
) {
    fun turnOn() {
        isTurnOn = true
        temperature = 95
    }

    fun turnOff() {
        isTurnOn = false
        temperature = 15
    }
}