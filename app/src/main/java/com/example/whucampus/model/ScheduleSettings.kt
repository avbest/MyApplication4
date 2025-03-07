package com.example.whucampus.model

data class ScheduleSettings(
    val showWeekend: Boolean = true,
    val totalWeeks: Int = 20,
    val sectionsPerDay: Int = 12
)