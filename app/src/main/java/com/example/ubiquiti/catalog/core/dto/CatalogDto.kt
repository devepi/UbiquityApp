package com.example.ubiquiti.catalog.core.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = CatalogDtoSerializer::class)
data class CatalogDto(
  val products: List<ProductDto>
)

object CatalogDtoSerializer : KSerializer<CatalogDto> {
  @OptIn(ExperimentalSerializationApi::class)
  override val descriptor: SerialDescriptor
    get() = buildClassSerialDescriptor("Catalog") {
      element("devices", listSerialDescriptor(ProductDto.serializer().descriptor))
    }

  override fun serialize(
    encoder: Encoder,
    value: CatalogDto
  ) {
    val composite = encoder.beginStructure(descriptor)
    composite.encodeSerializableElement(
      descriptor,
      index = 0,
      serializer = ListSerializer(ProductDtoSerializer),
      value = value.products
    )
    composite.endStructure(descriptor)
  }

  override fun deserialize(decoder: Decoder): CatalogDto {
    val composite = decoder.beginStructure(descriptor)

    var products: List<ProductDto> = emptyList()

    while (true) {
      when (val index = composite.decodeElementIndex(descriptor)) {
        CompositeDecoder.DECODE_DONE -> break
        0 -> {
          products = composite.decodeSerializableElement(
            descriptor,
            index = index,
            deserializer = ListSerializer(ProductDtoSerializer)
          )
        }

        else -> throw SerializationException("Unexpected index $index")
      }
    }

    composite.endStructure(descriptor)

    return CatalogDto(products)
  }

}
