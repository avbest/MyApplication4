package com.example.whucampus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.whucampus.model.WhucampusCourse
import com.example.whucampus.ui.schedule.ScheduleEditScreen
import com.example.whucampus.ui.theme.ZhiLanTheme

class ScheduleEditActivity : ComponentActivity() {
    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val course = intent.getParcelableExtra<WhucampusCourse>("course")

        setContent {
            ZhiLanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScheduleEditScreen(
                        course = course,
                        onSave = { newCourse ->
                            if (course == null) {
                                viewModel.addCourse(newCourse)
                            } else {
                                viewModel.updateCourse(newCourse)
                            }
                            finish()
                        },
                        onCancel = { finish() }
                    )
                }
            }
        }
    }

    companion object {
        fun createIntent(context: Context, course: WhucampusCourse? = null): Intent {
            return Intent(context, ScheduleEditActivity::class.java).apply {
                putExtra("course", course)
            }
        }
    }
}