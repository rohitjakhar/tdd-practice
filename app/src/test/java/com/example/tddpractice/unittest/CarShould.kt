package com.example.tddpractice.unittest

import com.example.tddpractice.Car
import com.example.tddpractice.Engine
import com.example.tddpractice.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CarShould {

    private val engine: Engine = mock()
    private val car = Car(5.0, engine)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()
    @Test
    fun looseFuelWhenTurnsOn() {
        car.turnOn()

        assertEquals(4.5, car.fule)
    }

    @Test
    fun turnOnItsEngine() = runBlocking {
        car.turnOn()

        verify(engine, times(1)).turnOn()
    }
}
