package com.example.whucampus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whucampus.model.WhucampusCourse
import com.example.whucampus.model.ScheduleSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val _courses = MutableStateFlow<List<WhucampusCourse>>(emptyList())
    val courses: StateFlow<List<WhucampusCourse>> = _courses.asStateFlow()

    private val _currentWeek = MutableStateFlow(1)
    val currentWeek: StateFlow<Int> = _currentWeek.asStateFlow()

    private val _settings = MutableStateFlow(ScheduleSettings())
    val settings: StateFlow<ScheduleSettings> = _settings

    fun addCourse(course: WhucampusCourse) {
        viewModelScope.launch {
            _courses.update { currentList ->
                currentList + course
            }
        }
    }

    fun updateCourse(course: WhucampusCourse) {
        viewModelScope.launch {
            _courses.update { currentList ->
                currentList.map { if (it.id == course.id) course else it }
            }
        }
    }

    fun deleteCourse(courseId: Int) {
        viewModelScope.launch {
            _courses.update { currentList ->
                currentList.filter { it.id != courseId }
            }
        }
    }

    fun setCurrentWeek(week: Int) {
        viewModelScope.launch {
            _currentWeek.value = week.coerceIn(1, 20)
        }
    }
}