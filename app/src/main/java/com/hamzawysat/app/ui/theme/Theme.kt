package com.hamzawysat.app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val Scheme=lightColorScheme(primary=Color(0xFFFF6A00),onPrimary=Color.White,secondary=Color(0xFF121212),background=Color(0xFFF7F7F7),surface=Color.White)
@Composable fun HamzawySatTheme(content:@Composable ()->Unit){MaterialTheme(colorScheme=Scheme,content=content)}
