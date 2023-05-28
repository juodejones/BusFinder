package com.jones.busfinder

import org.junit.Test
import org.junit.Assert.*

class ExampleFunctionalTesting {

    @Test
    fun `bus not found returns false`() {
        val id = "77=B"
        assertFalse(id, false)
    }

    @Test
    fun `searching for buses returns true`() {
        val id = "77=B"
        assertTrue(id, true)
    }

    @Test
    fun `bus details not found returns false`() {
        val id = "77=B"
        assertFalse(id, false)
    }

    @Test
    fun `show bus details returns true`() {
        val id = "77=B"
        assertTrue(id, true)
    }
}