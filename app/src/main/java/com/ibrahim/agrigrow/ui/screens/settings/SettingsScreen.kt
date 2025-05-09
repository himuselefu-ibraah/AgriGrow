package com.ibrahim.agrigrow.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.ibrahim.agrigrow.navigation.ROUT_HOME
import com.ibrahim.agrigrow.ui.theme.AgriGrowTheme
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    // Column layout for the settings
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title for Settings
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Toggle for Dark/Light Theme
        Text(text = "Enable Dark Theme:")
        Switch(
            checked = isDarkTheme,
            onCheckedChange = {
                // Trigger theme toggle
                onThemeToggle(it)
            },
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Spacer for spacing
        Spacer(modifier = Modifier.height(32.dp))

        // Button to navigate back to Home
        Button(onClick = {
            navController.navigate(ROUT_HOME) {
                popUpTo(ROUT_HOME) { inclusive = true }
            }
        }) {
            Text(text = "Go Back to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AgriGrowTheme(darkTheme = false) {
        SettingsScreen(
            navController = rememberNavController(),
            isDarkTheme = false,
            onThemeToggle = {}
        )
    }
}


