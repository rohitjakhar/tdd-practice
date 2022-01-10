package com.example.tddpractice.unittest

import com.example.tddpractice.Engine
import com.example.tddpractice.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class EngineShould {

    private val engine = Engine()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()
    @Test
    fun turnOn() = runBlocking {
        engine.turnOn()

        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun riseTheTemperatureWhenItsTurnsOn() = runBlocking {
        engine.turnOn()

        assertEquals(95, engine.temperature)
    }
}
