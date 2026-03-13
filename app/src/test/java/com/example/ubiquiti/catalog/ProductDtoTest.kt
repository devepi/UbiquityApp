package com.example.ubiquiti.catalog

import com.example.ubiquiti.catalog.core.dto.ProductDto
import com.example.ubiquiti.catalog.core.dto.ProductLineDto
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDtoTest {
  @Test
  fun serializationTest() {
    val json = Json { prettyPrint = true }

    val dto = ProductDto(
      "e97640c1-e4fc-49cf-a537-682843ce96e9",
      "Enterprise Network Video Recorder",
      "VPN",
      listOf("AF-11FX", "AF11FX"),
      "1b4cc56dda2c98bd71586ed5cc821d5a",
      "d8d7f2d2-5a6b-418b-af6d-35d63fe3e09a",
      "console",
      ProductLineDto(
        "unifi-protect",
        "UniFi Protect",
      ),
    )
    val serialized = json.encodeToString(ProductDto.serializer(), dto)
    println("Serialized JSON:\n$serialized")
    val deserialized = json.decodeFromString(ProductDto.serializer(), serialized)
    println("\nDeserialized Object:\n$deserialized")
    assertEquals(deserialized, dto)
  }
}