package com.example.zhilan.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.zhilan.model.Course
import com.example.zhilan.model.WeekType

class CourseRepository(context: Context) {
    private val dbHelper = CourseDBHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addCourse(course: Course): Long {
        val values = ContentValues().apply {
            put("name", course.name)
            put("teacher", course.teacher)
            put("classroom", course.location)
            put("begintime", course.startSection)
            put("sum", course.endSection - course.startSection + 1)
            put("period", course.dayOfWeek)
            put("color", course.color.toString())
            
            // 生成周数字符串
            val weekStr = StringBuilder()
            for (i in 1..20) { // 默认20周
                if (i in course.startWeek..course.endWeek) {
                    when (course.weekType) {
                        WeekType.ALL -> weekStr.append("1")
                        WeekType.ODD -> weekStr.append(if (i % 2 == 1) "1" else "0")
                        WeekType.EVEN -> weekStr.append(if (i % 2 == 0) "1" else "0")
                    }
                } else {
                    weekStr.append("0")
                }
            }
            put("week", weekStr.toString())
            
            // 设置单双周标记
            put("isOddWeek", if (course.weekType == WeekType.ODD) 1 else 0)
            put("isDoubleWeek", if (course.weekType == WeekType.EVEN) 1 else 0)
        }
        
        return db.insert("coursedata", null, values)
    }

    fun updateCourse(course: Course): Int {
        val values = ContentValues().apply {
            put("name", course.name)
            put("teacher", course.teacher)
            put("classroom", course.location)
            put("begintime", course.startSection)
            put("sum", course.endSection - course.startSection + 1)
            put("period", course.dayOfWeek)
            put("color", course.color.toString())
            
            // 生成周数字符串
            val weekStr = StringBuilder()
            for (i in 1..20) { // 默认20周
                if (i in course.startWeek..course.endWeek) {
                    when (course.weekType) {
                        WeekType.ALL -> weekStr.append("1")
                        WeekType.ODD -> weekStr.append(if (i % 2 == 1) "1" else "0")
                        WeekType.EVEN -> weekStr.append(if (i % 2 == 0) "1" else "0")
                    }
                } else {
                    weekStr.append("0")
                }
            }
            put("week", weekStr.toString())
            
            // 设置单双周标记
            put("isOddWeek", if (course.weekType == WeekType.ODD) 1 else 0)
            put("isDoubleWeek", if (course.weekType == WeekType.EVEN) 1 else 0)
        }
        
        return db.update("coursedata", values, "id = ?", arrayOf(course.id.toString()))
    }

    fun deleteCourse(courseId: Int): Int {
        return db.delete("coursedata", "id = ?", arrayOf(courseId.toString()))
    }

    fun getAllCourses(): List<Course> {
        val courses = mutableListOf<Course>()
        val cursor = db.query("coursedata", null, null, null, null, null, null)
        
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val name = it.getString(it.getColumnIndexOrThrow("name"))
                val teacher = it.getString(it.getColumnIndexOrThrow("teacher"))
                val classroom = it.getString(it.getColumnIndexOrThrow("classroom"))
                val beginTime = it.getInt(it.getColumnIndexOrThrow("begintime"))
                val sum = it.getInt(it.getColumnIndexOrThrow("sum"))
                val period = it.getInt(it.getColumnIndexOrThrow("period"))
                val color = it.getString(it.getColumnIndexOrThrow("color")).toIntOrNull() ?: 0
                val weekStr = it.getString(it.getColumnIndexOrThrow("week"))
                val isOddWeek = it.getInt(it.getColumnIndexOrThrow("isOddWeek")) == 1
                val isDoubleWeek = it.getInt(it.getColumnIndexOrThrow("isDoubleWeek")) == 1

                // 解析周数信息
                var startWeek = 1
                var endWeek = 20
                weekStr.forEachIndexed { index, c ->
                    if (c == '1') {
                        startWeek = minOf(startWeek, index + 1)
                        endWeek = maxOf(endWeek, index + 1)
                    }
                }

                // 确定周类型
                val weekType = when {
                    isOddWeek -> WeekType.ODD
                    isDoubleWeek -> WeekType.EVEN
                    else -> WeekType.ALL
                }

                courses.add(Course(
                    id = id,
                    name = name,
                    teacher = teacher,
                    location = classroom,
                    dayOfWeek = period,
                    startSection = beginTime,
                    endSection = beginTime + sum - 1,
                    startWeek = startWeek,
                    endWeek = endWeek,
                    weekType = weekType,
                    color = color
                ))
            }
        }
        return courses
    }
}