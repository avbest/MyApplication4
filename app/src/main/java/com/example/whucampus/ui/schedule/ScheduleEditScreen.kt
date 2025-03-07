package com.example.whucampus.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whucampus.model.WhucampusCourse
import com.example.whucampus.model.WhucampusWeekType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleEditScreen(
    course: WhucampusCourse?,
    onSave: (WhucampusCourse) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(course?.name ?: "") }
    var teacher by remember { mutableStateOf(course?.teacher ?: "") }
    var location by remember { mutableStateOf(course?.location ?: "") }
    var dayOfWeek by remember { mutableStateOf(course?.dayOfWeek ?: 1) }
    var startSection by remember { mutableStateOf(course?.startSection ?: 1) }
    var endSection by remember { mutableStateOf(course?.endSection ?: 2) }
    var startWeek by remember { mutableStateOf(course?.startWeek ?: 1) }
    var endWeek by remember { mutableStateOf(course?.endWeek ?: 16) }
    var weekType by remember { mutableStateOf(course?.weekType ?: WhucampusWeekType.ALL) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("课程名称") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = teacher,
            onValueChange = { teacher = it },
            label = { Text("教师") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("上课地点") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 星期选择
            OutlinedTextField(
                value = dayOfWeek.toString(),
                onValueChange = { 
                    it.toIntOrNull()?.let { day ->
                        if (day in 1..7) dayOfWeek = day
                    }
                },
                label = { Text("星期") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 节次选择
            OutlinedTextField(
                value = startSection.toString(),
                onValueChange = { 
                    it.toIntOrNull()?.let { start ->
                        if (start in 1..12) startSection = start
                    }
                },
                label = { Text("开始节") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = endSection.toString(),
                onValueChange = { 
                    it.toIntOrNull()?.let { end ->
                        if (end in startSection..12) endSection = end
                    }
                },
                label = { Text("结束节") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 周数选择
            OutlinedTextField(
                value = startWeek.toString(),
                onValueChange = { 
                    it.toIntOrNull()?.let { start ->
                        if (start in 1..20) startWeek = start
                    }
                },
                label = { Text("开始周") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = endWeek.toString(),
                onValueChange = { 
                    it.toIntOrNull()?.let { end ->
                        if (end in startWeek..20) endWeek = end
                    }
                },
                label = { Text("结束周") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 单双周选择
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WhucampusWeekType.values().forEach { type ->
                FilterChip(
                    selected = weekType == type,
                    onClick = { weekType = type },
                    label = {
                        Text(
                            when (type) {
                                WhucampusWeekType.ALL -> "每周"
                                WhucampusWeekType.ODD -> "单周"
                                WhucampusWeekType.EVEN -> "双周"
                            }
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onCancel) {
                Text("取消")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onSave(
                        WhucampusCourse(
                            id = course?.id ?: 0,
                            name = name,
                            teacher = teacher,
                            location = location,
                            dayOfWeek = dayOfWeek,
                            startSection = startSection,
                            endSection = endSection,
                            startWeek = startWeek,
                            endWeek = endWeek,
                            weekType = weekType
                        )
                    )
                },
                enabled = name.isNotBlank()
            ) {
                Text("保存")
            }
        }
    }
}