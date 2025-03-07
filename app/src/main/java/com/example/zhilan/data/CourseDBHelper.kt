package com.example.zhilan.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CourseDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // 创建课程表
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS coursedata (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                teacher TEXT,
                classroom TEXT,
                begintime INTEGER,
                sum INTEGER,
                period INTEGER,
                color TEXT,
                week TEXT,
                isOddWeek INTEGER DEFAULT 0,
                isDoubleWeek INTEGER DEFAULT 0
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 简单的升级策略：删除旧表，创建新表
        db.execSQL("DROP TABLE IF EXISTS coursedata")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "course.db"
        const val DATABASE_VERSION = 1
    }
}