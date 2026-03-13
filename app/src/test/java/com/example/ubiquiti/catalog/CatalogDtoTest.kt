package com.example.ubiquiti.catalog

import com.example.ubiquiti.catalog.core.dto.CatalogDto
import com.example.ubiquiti.catalog.core.dto.ProductDto
import com.example.ubiquiti.catalog.core.dto.ProductLineDto
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class CatalogDtoTest {
  @Test
  fun serializationTest() {
    val dto = CatalogDto(
      products = listOf(
        ProductDto(
          id = "cdd9b268-13a2-404f-b295-9e8d0c605de0",
          name = "airCube AC",
          abbrev = "airCube",
          shortNames = listOf("airCube", "AC"),
          image = "1435ef4d1b7caf065251ea2fcab512fb",
          thumb = "0d482159-5482-4cf7-b8ab-cfd1c798bab9",
          type = "access-point",
          line = ProductLineDto(
            id = "unifi-protect",
            name = "UniFi Protect",
          )
        )
      )
    )
    val json = Json { prettyPrint = true }
    val serialized = json.encodeToString(CatalogDto.serializer(), dto)
    println("Serialized JSON:\n$serialized")
    val deserialized = json.decodeFromString(CatalogDto.serializer(), serialized)
    println("\nDeserialized Object:\n$deserialized")
    assertEquals(deserialized, dto)
  }
}