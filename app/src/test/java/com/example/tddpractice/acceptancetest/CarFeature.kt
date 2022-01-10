package com.example.tddpractice.acceptancetest

import com.example.tddpractice.Car
import com.example.tddpractice.Engine
import com.example.tddpractice.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Rule
import org.junit.Test

class CarFeature {

    private val engine = Engine()
    private val car = Car(6.0, engine)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()


    @Test
    fun carIsLoosingFuelWhenItsTurnsOn() = runBlocking{
        car.turnOn()

        assertEquals(5.5, car.fule)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreaseAndTemperature() = runBlocking{
        car.turnOn()

        coroutinesTestRule.testScheduler.apply { advanceTimeBy(6000); runCurrent() }
        assertEquals(95, car.engine.temperature)
        assertTrue(car.engine.isTurnedOn)
    }
}