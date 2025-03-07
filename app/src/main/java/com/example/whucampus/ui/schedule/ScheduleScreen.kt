package com.example.whucampus.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.whucampus.model.WhucampusCourse
import com.example.whucampus.model.ScheduleSettings
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    settings: ScheduleSettings,
    courses: List<WhucampusCourse>,
    currentWeek: Int,
    onCourseClick: (WhucampusCourse) -> Unit,
    onWeekChange: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 周数选择器
            WeekSelector(currentWeek = currentWeek, onWeekChange = onWeekChange)
            
            // 课程表
            Row(modifier = Modifier.weight(1f)) {
                // 时间列
                TimeColumn()
                
                // 课程网格
                CourseGrid(
                    courses = courses,
                    currentWeek = currentWeek,
                    onCourseClick = onCourseClick
                )
            }
        }

        // 添加课程按钮
        FloatingActionButton(
            onClick = { onCourseClick(WhucampusCourse()) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "添加课程")
        }
    }
}

@Composable
fun WeekSelector(
    currentWeek: Int,
    onWeekChange: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20) { week ->
            val isSelected = week + 1 == currentWeek
            FilterChip(
                selected = isSelected,
                onClick = { onWeekChange(week + 1) },
                label = { Text("第${week + 1}周") }
            )
        }
    }
}

@Composable
fun TimeColumn() {
    Column(
        modifier = Modifier
            .width(40.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 空白格子，对应星期行
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.surface)
        )
        
        // 课节数
        repeat(12) { section ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (section + 1).toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CourseGrid(
    courses: List<WhucampusCourse>,
    currentWeek: Int,
    onCourseClick: (WhucampusCourse) -> Unit
) {
    Row {
        // 星期几
        DayOfWeek.values().forEach { day ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 星期标题
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (day) {
                            DayOfWeek.MONDAY -> "周一"
                            DayOfWeek.TUESDAY -> "周二"
                            DayOfWeek.WEDNESDAY -> "周三"
                            DayOfWeek.THURSDAY -> "周四"
                            DayOfWeek.FRIDAY -> "周五"
                            DayOfWeek.SATURDAY -> "周六"
                            DayOfWeek.SUNDAY -> "周日"
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                // 课程格子
                Box(modifier = Modifier.weight(1f)) {
                    courses.filter { course ->
                        course.dayOfWeek == day.value &&
                        course.startWeek <= currentWeek &&
                        course.endWeek >= currentWeek
                    }.forEach { course ->
                        CourseCard(
                            course = course,
                            modifier = Modifier
                                .padding(1.dp)
                                .fillMaxWidth()
                                .height(((course.endSection - course.startSection + 1) * 40).dp)
                                .absoluteOffset(y = ((course.startSection - 1) * 40).dp)
                                .clickable { onCourseClick(course) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCard(
    course: WhucampusCourse,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            if (course.location.isNotEmpty()) {
                Text(
                    text = course.location,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}