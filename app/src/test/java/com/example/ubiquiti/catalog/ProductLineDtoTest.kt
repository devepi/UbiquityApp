package com.example.ubiquiti.catalog

import com.example.ubiquiti.catalog.core.dto.ProductLineDto
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductLineDtoTest {
  @Test
  fun serializationTest() {
    val dto = ProductLineDto("unifi-protect", "UniFi Protect")
    val json = Json { prettyPrint = true }
    val serialized = json.encodeToString(ProductLineDto.serializer(), dto)
    println("Serialized JSON:\n$serialized")
    val deserialized = json.decodeFromString(ProductLineDto.serializer(), serialized)
    println("\nDeserialized Object:\n$deserialized")
    assertEquals(deserialized, dto)
  }
}