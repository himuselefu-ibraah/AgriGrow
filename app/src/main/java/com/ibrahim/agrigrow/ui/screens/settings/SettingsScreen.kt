package com.ibrahim.agrigrow.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Settings", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Notification toggle row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { notificationsEnabled = !notificationsEnabled },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Notifications, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Text("Enable Notifications", modifier = Modifier.weight(1f))
                Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
            }

            Divider()

            // Setting items with navigation
            SettingsItem(icon = Icons.Default.Edit, text = "Edit Profile", onClick = { navController.navigate("profile") })
            SettingsItem(icon = Icons.Default.ThumbUp, text = "Privacy Policy", onClick = { navController.navigate("privacy_policy") })
            SettingsItem(icon = Icons.Default.ThumbUp, text = "Help & Support", onClick = { navController.navigate("help_support") })
            SettingsItem(icon = Icons.Default.ExitToApp, text = "Logout", textColor = Color.Red, onClick = onLogoutClick)
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    text: String,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = textColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = textColor, style = MaterialTheme.typography.bodyLarge)
    }
}

