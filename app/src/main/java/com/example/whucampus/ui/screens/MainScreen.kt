package com.example.whucampus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        bottomBar = { 
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.DateRange, contentDescription = "课程") },
                    label = { Text("课程") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Info, contentDescription = "状态") },
                    label = { Text("状态") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "我的") },
                    label = { Text("我的") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> CourseScreen()
                1 -> StatusScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun CourseScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "本周课程",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = "请设置开学日期后再使用课程表", modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun StatusScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        WeatherCard()
        Spacer(modifier = Modifier.height(16.dp))
        FunctionGrid()
    }
}

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { /* TODO: 处理登录点击 */ }
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "未登录",
                    modifier = Modifier.size(48.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = "未登录",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "点击登录",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        
        MenuList()
    }
}

@Composable
fun WeatherCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "天气",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "定位服务未开启",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FunctionGrid() {
    val functions = listOf(
        "图书馆" to Icons.Filled.LocalLibrary,
        "校巴" to Icons.Filled.DirectionsBus,
        "课程表" to Icons.Filled.Event,
        "E卡" to Icons.Filled.CreditCard,
        "运动" to Icons.Filled.DirectionsRun,
        "成绩" to Icons.Filled.Assessment
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(functions.size) { index ->
            val (name, icon) = functions[index]
            FunctionItem(name = name, icon = icon)
        }
    }
}

@Composable
fun FunctionItem(name: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { /* TODO: 处理功能点击 */ }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = name,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun MenuList() {
    val menuItems = listOf(
        "课程评价" to Icons.Filled.Star,
        "资料共享" to Icons.Filled.Folder,
        "设置" to Icons.Filled.Settings
    )

    Column {
        menuItems.forEach { (title, icon) ->
            ListItem(
                headlineContent = { Text(title) },
                leadingContent = { 
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.clickable { /* TODO: 处理菜单点击 */ }
            )
            Divider()
        }
    }
}