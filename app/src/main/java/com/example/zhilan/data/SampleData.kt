package com.example.zhilan.data

import com.example.zhilan.model.Course
import com.example.zhilan.model.WeekType

object SampleData {
    fun getSampleCourses(): List<Course> = listOf(
        Course(
            name = "高等数学",
            location = "教学楼A101",
            teacher = "张教授",
            dayOfWeek = 1,
            startSection = 1,
            endSection = 2,
            startWeek = 1,
            endWeek = 16,
            weekType = WeekType.ALL,
            color = 0xFF2196F3.toInt()
        ),
        Course(
            name = "大学物理",
            location = "教学楼B202",
            teacher = "李教授",
            dayOfWeek = 2,
            startSection = 3,
            endSection = 4,
            startWeek = 1,
            endWeek = 16,
            weekType = WeekType.ALL,
            color = 0xFF4CAF50.toInt()
        ),
        Course(
            name = "程序设计",
            location = "实验楼C303",
            teacher = "王教授",
            dayOfWeek = 3,
            startSection = 5,
            endSection = 6,
            startWeek = 1,
            endWeek = 16,
            weekType = WeekType.ALL,
            color = 0xFFE91E63.toInt()
        )
    )
}