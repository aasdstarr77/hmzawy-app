package com.hamzawysat.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.hamzawysat.app.ui.HamzawySatRoot
import com.hamzawysat.app.ui.theme.HamzawySatTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint class MainActivity : ComponentActivity() { override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { HamzawySatTheme { CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) { HamzawySatRoot() } } } } }
