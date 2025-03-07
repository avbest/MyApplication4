package com.example.whucampus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class StatusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("欢迎使用WHU校园助手")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            startActivity(Intent(this@StatusActivity, MainActivity::class.java))
                        }
                    ) {
                        Text("进入主页")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            startActivity(Intent(this@StatusActivity, ScheduleActivity::class.java))
                        }
                    ) {
                        Text("课程表")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            startActivity(Intent(this@StatusActivity, ScheduleEditActivity::class.java))
                        }
                    ) {
                        Text("编辑课程表")
                    }
                }
            }
        }
    }
}