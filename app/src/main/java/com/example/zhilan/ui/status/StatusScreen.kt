package com.example.zhilan.ui.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zhilan.model.WeekType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext

@Composable
fun StatusScreen(
    onSportsClick: () -> Unit,
    onGradeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        // 状态栏安全区域
        Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars))
        
        // 顶部问候语和日期
        TopSection()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 知澜建议（暂时为空）
        SuggestionSection()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 功能按钮
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onSportsClick) {
                Text("体育")
            }
            Button(onClick = onGradeClick) {
                Text("成绩")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 今日课程
        TodayCourseSection()
    }
}

@Composable
fun TopSection() {
    val currentTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 E")
    
    Column {
        Text(
            text = "上午好！",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "今天是${currentTime.format(dateFormatter)}",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun SuggestionSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "知澜建议",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CourseCard(
    courseName: String,
    lessonNumber: String,
    time: String,
    location: String,
    teacher: String,
    gradientColors: List<Color>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.horizontalGradient(gradientColors)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = courseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = lessonNumber,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = location,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = 2.dp)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = teacher,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
    }
}

@Composable
fun TodayCourseSection() {
    val viewModel: ScheduleViewModel = viewModel(factory = ScheduleViewModelFactory(LocalContext.current))
    val currentWeek by viewModel.currentWeek.collectAsState()
    val courses by viewModel.courses.collectAsState()
    val calendar = Calendar.getInstance()
    val dayOfWeek = if (calendar.get(Calendar.DAY_OF_WEEK) == 1) 7 else calendar.get(Calendar.DAY_OF_WEEK) - 1
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "今天是第${currentWeek}周，星期${getDayOfWeekText(dayOfWeek)}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        val todayCourses = courses.filter { course ->
            course.dayOfWeek == dayOfWeek &&
            currentWeek >= course.startWeek &&
            currentWeek <= course.endWeek &&
            when (course.weekType) {
                WeekType.ALL -> true
                WeekType.ODD -> currentWeek % 2 == 1
                WeekType.EVEN -> currentWeek % 2 == 0
            }
        }.sortedBy { it.startSection }
        
        if (todayCourses.isEmpty()) {
            Text(
                text = "今天没有课程安排",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            todayCourses.forEach { course ->
                CourseCard(
                    courseName = course.name,
                    lessonNumber = "第${course.startSection}-${course.endSection}节",
                    time = getTimeRange(course.startSection, course.endSection),
                    location = course.location,
                    teacher = course.teacher,
                    gradientColors = getGradientColors(course.color)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

private fun getDayOfWeekText(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        1 -> "一"
        2 -> "二"
        3 -> "三"
        4 -> "四"
        5 -> "五"
        6 -> "六"
        7 -> "日"
        else -> "一"
    }
}

private fun getTimeRange(startSection: Int, endSection: Int): String {
    val sectionTimes = listOf(
        "08:00-08:45", "08:55-09:40", "10:00-10:45", "10:55-11:40",
        "14:00-14:45", "14:55-15:40", "16:00-16:45", "16:55-17:40",
        "19:00-19:45", "19:55-20:40", "20:50-21:35", "21:45-22:30"
    )
    return "${sectionTimes[startSection - 1].split("-")[0]}-${sectionTimes[endSection - 1].split("-")[1]}"
}

private fun getGradientColors(colorIndex: Int): List<Color> {
    val gradientPairs = listOf(
        Pair(Color(0xFF2C3E50), Color(0xFF3498DB)),
        Pair(Color(0xFF8E44AD), Color(0xFF9B59B6)),
        Pair(Color(0xFF16A085), Color(0xFF1ABC9C)),
        Pair(Color(0xFFD35400), Color(0xFFE67E22)),
        Pair(Color(0xFF27AE60), Color(0xFF2ECC71)),
        Pair(Color(0xFF2980B9), Color(0xFF3498DB))
    )
    return if (colorIndex in gradientPairs.indices) {
        listOf(gradientPairs[colorIndex].first, gradientPairs[colorIndex].second)
    } else {
        listOf(gradientPairs[0].first, gradientPairs[0].second)
    }
}