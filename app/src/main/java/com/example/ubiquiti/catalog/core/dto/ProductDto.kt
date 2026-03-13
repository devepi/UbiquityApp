package com.example.ubiquiti.catalog.core.dto

import com.example.ubiquiti.catalog.core.model.Product
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

@Serializable(with = ProductDtoSerializer::class)
data class ProductDto(
  val id: String = "",
  val name: String = "",
  val abbrev: String = "",
  val shortNames: List<String> = listOf(),
  val image: String = "",
  val thumb: String = "",
  val type: String = "",
  val line: ProductLineDto? = null,
) {
  fun toDomain() = Product(
    id,
    name,
    abbrev,
    shortNames,
    image,
    thumb,
    type,
    line?.toDomain(),
  )
}

object ProductDtoSerializer : KSerializer<ProductDto> {
  override val descriptor: SerialDescriptor
    get() = buildClassSerialDescriptor("Product") {
      element<String>("id")
      element<String>("name")
      element<String>("abbrev")
      element<String>("shortNames")
      element<String>("image")
      element<String>("thumb")
      element<String>("deviceType")
      element("line", ProductLineDto.serializer().descriptor)
    }

  override fun serialize(
    encoder: Encoder,
    value: ProductDto
  ) {
    val jsonEncoder = encoder as? JsonEncoder
      ?: error("Can be serialized only by JSON")

    val obj = buildJsonObject {
      put("id", value.id)
      putJsonObject("product") {
        put("name", value.name)
        put("abbrev", value.abbrev)
      }
      putJsonArray("shortnames") {
        for (name in value.shortNames)
          add(name)
      }
      putJsonObject("images") {
        put("default", value.image)
      }
      putJsonObject("icon") {
        put("id", value.thumb)
      }
      put("deviceType", value.type)

      value.line?.let {
        put("line", jsonEncoder.json.encodeToJsonElement(it))
      }
    }

    jsonEncoder.encodeJsonElement(obj)
  }

  override fun deserialize(decoder: Decoder): ProductDto {
    val jsonDecoder = decoder as? JsonDecoder
      ?: error("Can be deserialized only by JSON")

    val element = jsonDecoder.decodeJsonElement().jsonObject

    val productJson = element["product"]
      ?.jsonObject

    return ProductDto(
      id = element["id"]?.jsonPrimitive?.content ?: "",

      name = productJson
        ?.get("name")
        ?.jsonPrimitive
        ?.content
        ?: "",

      abbrev = productJson
        ?.get("abbrev")
        ?.jsonPrimitive
        ?.content
        ?: "",

      shortNames = element["shortnames"]
        ?.jsonArray
        ?.mapNotNull { it.jsonPrimitive.content }
        ?: listOf(),

      image = element["images"]
        ?.jsonObject
        ?.get("default")
        ?.jsonPrimitive
        ?.content
        ?: "",

      thumb = element["icon"]
        ?.jsonObject
        ?.get("id")
        ?.jsonPrimitive
        ?.content
        ?: "",

      type = element["deviceType"]
        ?.jsonPrimitive
        ?.content
        ?: "",

      element["line"]?.let {
        jsonDecoder.json.decodeFromJsonElement<ProductLineDto>(it)
      })
  }
}
