package com.example.zhilan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.zhilan.model.ScheduleSettings
import com.example.zhilan.ui.settings.SettingsScreen
import com.example.zhilan.ui.theme.ZhiLanTheme

class SettingsActivity : ComponentActivity() {
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZhiLanTheme {
                val settings by viewModel.settings.collectAsState()
                
                SettingsScreen(
                    settings = settings,
                    onSettingsChange = viewModel::updateSettings
                )
            }
        }
    }
}