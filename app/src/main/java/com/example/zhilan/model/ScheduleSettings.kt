package com.example.zhilan.model

/**
 * 课程表设置类，用于存储课程表的各种配置
 */
data class ScheduleSettings(
    // 是否显示周末
    var showWeekend: Boolean = true,
    
    // 学期总周数
    var totalWeeks: Int = 20,
    
    // 每天课程节数
    var sectionsPerDay: Int = 12,
    
    // 上午课程节数
    var morningClasses: Int = 4,
    
    // 下午课程节数
    var afternoonClasses: Int = 4,
    
    // 晚上课程节数
    var eveningClasses: Int = 2,
    
    // 每节课时长(分钟)
    var classDuration: Int = 45,
    
    // 课间休息时长(分钟)
    var breakDuration: Int = 10,
    
    // 开学日期(yyyy-MM-dd格式)
    var termStartDate: String = "",
    
    // 课程时间表
    var classTimes: List<String> = listOf(
        "08:00-08:45", // 第1节
        "08:55-09:40", // 第2节
        "10:00-10:45", // 第3节
        "10:55-11:40", // 第4节
        "14:00-14:45", // 第5节
        "14:55-15:40", // 第6节
        "16:00-16:45", // 第7节
        "16:55-17:40", // 第8节
        "19:00-19:45", // 第9节
        "19:55-20:40", // 第10节
        "20:50-21:35"  // 第11节
    )
) {
    
    // 获取指定节次的时间
    fun getClassTime(section: Int): String {
        return if (section > 0 && section <= classTimes.size) {
            classTimes[section - 1]
        } else {
            ""
        }
    }
}