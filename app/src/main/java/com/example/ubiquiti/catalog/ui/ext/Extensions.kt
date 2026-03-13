package com.example.ubiquiti.catalog.ui.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SetDartStatusBar() {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      WindowCompat.getInsetsController((view.context as Activity).window, view)
        .isAppearanceLightStatusBars = true
    }
  }
}

@Composable
inline fun <reified VM : ViewModel> activityViewModel(): VM {
  return viewModel<VM>(
    viewModelStoreOwner = findActivity()
  )
}

@Composable
fun findActivity(): ComponentActivity {
  return LocalContext.current.findActivity()
}

fun Context.findActivity(): ComponentActivity {
  var context = this
  while (context is ContextWrapper) {
    if (context is ComponentActivity) return context
    context = context.baseContext
  }
  error("Not a context of activity")
}