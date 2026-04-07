package com.example.ca1and

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    var menuExpanded by remember { mutableStateOf(false) }

    val topics = listOf("Android", "Kotlin", "Jetpack Compose", "UI/UX", "Firebase", "Web Dev")

    Column(modifier = Modifier.fillMaxSize()) {

        // Top App Bar
        TopAppBar(
            title = { Text("Dashboard") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Profile") },
                        onClick = {
                            Toast.makeText(context, "Profile Clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Settings") },
                        onClick = {
                            Toast.makeText(context, "Settings Clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            Toast.makeText(context, "Logout Clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false
                        }
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(text = "Topics", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                topics.forEach { topic ->
                    SuggestionChip(
                        onClick = {},
                        label = { Text(topic) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "My Courses", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            CourseCard(
                title = "CSE225: Android Dev",
                description = "Learn modern Android development using Kotlin.",
                status = "In Progress"
            )

            CourseCard(
                title = "CSE326: Web Tech",
                description = "Full-stack web development basics.",
                status = "New"
            )

            CourseCard(
                title = "INT108: Python",
                description = "Introduction to Python programming.",
                status = "Completed"
            )
        }
    }
}

@Composable
fun CourseCard(title: String, description: String, status: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = when (status) {
                        "New" -> Color(0xFFE3F2FD)
                        "In Progress" -> Color(0xFFFFF3E0)
                        else -> Color(0xFFE8F5E9)
                    }
                ) {
                    Text(
                        text = status,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}