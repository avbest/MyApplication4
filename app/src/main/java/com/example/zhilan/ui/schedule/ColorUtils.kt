package com.example.zhilan.ui.schedule

import androidx.compose.ui.graphics.Color

object ColorUtils {
    fun getGradientColors(colorIndex: Int): List<Color> {
        val gradientPairs = listOf(
            Pair(Color(0xFF2C3E50), Color(0xFF3498DB)),
            Pair(Color(0xFF8E44AD), Color(0xFF9B59B6)),
            Pair(Color(0xFF16A085), Color(0xFF1ABC9C)),
            Pair(Color(0xFFD35400), Color(0xFFE67E22)),
            Pair(Color(0xFF27AE60), Color(0xFF2ECC71)),
            Pair(Color(0xFF2980B9), Color(0xFF3498DB))
        )
        return if (colorIndex in gradientPairs.indices) {
            listOf(gradientPairs[colorIndex].first, gradientPairs[colorIndex].second)
        } else {
            listOf(gradientPairs[0].first, gradientPairs[0].second)
        }
    }
}