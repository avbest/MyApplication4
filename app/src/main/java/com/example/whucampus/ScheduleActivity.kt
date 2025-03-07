package com.example.whucampus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.whucampus.model.ScheduleSettings
import com.example.whucampus.model.WhucampusCourse
import com.example.whucampus.ui.schedule.ScheduleScreen
import com.example.whucampus.ui.theme.ZhiLanTheme

class ScheduleActivity : ComponentActivity() {
    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZhiLanTheme {
                val courses by viewModel.courses.collectAsState()
                val currentWeek by viewModel.currentWeek.collectAsState()
                val settings = ScheduleSettings()  // 使用默认设置

                ScheduleScreen(
                    settings = settings,
                    courses = courses,
                    currentWeek = currentWeek,
                    onCourseClick = { course ->
                        startActivity(
                            ScheduleEditActivity.createIntent(this, course)
                        )
                    },
                    onWeekChange = viewModel::setCurrentWeek
                )
            }
        }
    }
}