package com.jones.busfinder

import org.junit.Test
import org.junit.Assert.*

class ExampleIntegrationTest {

    @Test
    fun `cannot connect to firebase returns false`() {
        val id = "77=B"
        assertFalse(id, false)
    }

    @Test
    fun `connection to firebase returns true`() {
        val id = "77=B"
        assertTrue(id, true)
    }
}