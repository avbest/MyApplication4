package com.example.zhilan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zhilan.model.ScheduleSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _settings = MutableStateFlow(ScheduleSettings())
    val settings: StateFlow<ScheduleSettings> = _settings.asStateFlow()

    fun updateSettings(newSettings: ScheduleSettings) {
        viewModelScope.launch {
            _settings.value = newSettings
            // TODO: 在这里添加持久化存储逻辑
        }
    }
}