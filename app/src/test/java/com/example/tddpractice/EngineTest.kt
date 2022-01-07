package com.example.tddpractice

import org.junit.Test

import org.junit.Assert.*

class EngineTest {

    private val engine = Engine(2000, 189, 15, false)

    @Test
    fun engineTurnOn() {
        engine.turnOn()

        assertEquals(true, engine.isTurnOn)
        assertEquals(95, engine.temperature)
    }

    @Test
    fun engineTurnOff() {
        engine.turnOff()

        assertEquals(false, engine.isTurnOn)
        assertEquals(15, engine.temperature)
    }
}