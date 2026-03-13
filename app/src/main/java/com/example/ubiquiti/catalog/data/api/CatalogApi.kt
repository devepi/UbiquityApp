package com.example.ubiquiti.catalog.data.api

import com.example.ubiquiti.catalog.core.dto.CatalogDto
import retrofit2.http.GET

interface CatalogApi {

  @GET("public.json")
  suspend fun getCatalog(): CatalogDto

}
