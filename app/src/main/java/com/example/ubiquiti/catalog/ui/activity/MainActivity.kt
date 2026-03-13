package com.example.ubiquiti.catalog.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.feature.details.view.ProductDetailsScreen
import com.example.ubiquiti.catalog.feature.list.view.ProductListScreen
import com.example.ubiquiti.catalog.ui.navigation.Navigation
import com.example.ubiquiti.catalog.ui.navigation.ProductNavType
import com.example.ubiquiti.catalog.ui.theme.UbiquitiCatalogTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val view = LocalView.current
      SideEffect {
        WindowCompat.getInsetsController(window, view)
          .isAppearanceLightStatusBars = true
      }

      UbiquitiCatalogTheme {
        val navController = rememberNavController()
        NavHost(
          navController = navController,
          startDestination = Navigation.ProductList,
        ) {
          composable<Navigation.ProductList> { ProductListScreen(navController) }
          composable<Navigation.ProductDetails>(
            typeMap = mapOf(typeOf<Product>() to ProductNavType)
          ) { backStackEntry ->
            val product = backStackEntry.toRoute<Navigation.ProductDetails>().product
            ProductDetailsScreen(navController, product)
          }
        }
      }
    }
  }
}
