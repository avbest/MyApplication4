package com.example.zhilan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zhilan.ui.schedule.ScheduleScreen
import com.example.zhilan.ui.schedule.ScheduleViewModelFactory
import com.example.zhilan.ui.theme.ZhiLanTheme

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZhiLanTheme {
                val viewModel: ScheduleViewModel = viewModel(factory = ScheduleViewModelFactory(this@ScheduleActivity))
                
                ScheduleScreen(
                    courses = viewModel.courses.collectAsState().value,
                    currentWeek = viewModel.currentWeek.collectAsState().value,
                    onCourseClick = { course ->
                        startActivity(ScheduleEditActivity.createIntent(this@ScheduleActivity, course))
                    },
                    onWeekChange = { newWeek ->
                        viewModel.setCurrentWeek(newWeek)
                    },
                    onAddCourse = {
                        startActivity(ScheduleEditActivity.createIntent(this@ScheduleActivity))
                    }
                )
            }
        }
    }
}