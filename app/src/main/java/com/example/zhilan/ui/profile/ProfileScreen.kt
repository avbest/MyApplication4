package com.example.zhilan.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FunctionButton(
    val icon: ImageVector,
    val label: String,
    val color: Color,
    val onClick: () -> Unit
)

@Composable
fun ProfileScreen(
    onScheduleClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onBusClick: () -> Unit,
    onCardClick: () -> Unit,
    onSportsClick: () -> Unit,
    onGradeClick: () -> Unit
) {
    val buttons = listOf(
        FunctionButton(
            icon = Icons.Default.DateRange,
            label = "课程表",
            color = Color(0xFF2196F3),
            onClick = onScheduleClick
        ),
        FunctionButton(
            icon = Icons.Default.LocalLibrary,
            label = "图书馆",
            color = Color(0xFF4CAF50),
            onClick = onLibraryClick
        ),
        FunctionButton(
            icon = Icons.Default.DirectionsBus,
            label = "校车",
            color = Color(0xFFFFC107),
            onClick = onBusClick
        ),
        FunctionButton(
            icon = Icons.Default.CreditCard,
            label = "一卡通",
            color = Color(0xFFE91E63),
            onClick = onCardClick
        ),
        FunctionButton(
            icon = Icons.Default.DirectionsRun,
            label = "体育",
            color = Color(0xFF9C27B0),
            onClick = onSportsClick
        ),
        FunctionButton(
            icon = Icons.Default.Grade,
            label = "成绩",
            color = Color(0xFFFF5722),
            onClick = onGradeClick
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // 状态栏安全区域
        Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars))
        
        // 用户信息卡片
        UserInfoCard()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 功能按钮水平滚动列表
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(buttons) { button ->
                FunctionButtonCard(button)
            }
        }
    }
}

@Composable
fun UserInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "我的信息",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "点击登录/注册",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunctionButtonCard(button: FunctionButton) {
    Card(
        onClick = button.onClick,
        colors = CardDefaults.cardColors(
            containerColor = button.color
        ),
        modifier = Modifier.size(80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = button.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = button.label,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                textAlign = TextAlign.Center
            )
        }
    }
} 