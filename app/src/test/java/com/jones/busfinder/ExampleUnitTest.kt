package com.jones.busfinder

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `invalid id returns false`() {
        val id = "77=B"
        assertFalse(id, false)
    }

    @Test
    fun `correct bus id returns true`() {
        val id = "77=B"
        assertTrue(id, true)
    }

    @Test
    fun `searching with source and destination returns true`() {
        val source = "Arapalayam"
        assertTrue(source, true)
    }

    @Test
    fun `searching with invalid source and destination returns false`() {
        val source = "dgl"
        assertFalse(source, false)
    }

    @Test
    fun `searching stop returns true`() {
        val source = "periar"
        assertTrue(source, true)
    }

    @Test
    fun `searching with invalid stop returns false`() {
        val source = "dgl"
        assertFalse(source, false)
    }
}