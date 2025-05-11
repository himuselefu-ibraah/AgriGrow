package com.ibrahim.agrigrow.ui.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val items = listOf(
        Triple("Crop Calendar", Icons.Default.DateRange, "crops"),
        Triple("Fertilizer Guide",Icons.Default.Info , "fer"),
        Triple("Know Your Animal Health", Icons.Default.Info, "animal"),
        Triple("Pest & Diseases Alerts", Icons.Default.Warning, "pe"),
        Triple("Weather Updates", Icons.Default.Favorite, "weather"),
        Triple("Agriculture Guide", Icons.Default.Info, "agri"),
        Triple("Disease Detection", Icons.Default.Warning, "detect"),
        Triple("Know Your Farm Tools", Icons.Default.Info, "tool"),
        Triple("Irrigation Guide", Icons.Default.Info, "Ir"),
        Triple("YieldMax Fertilizer Tool", Icons.Default.Check, "calc"),
        Triple("Farm Profit Estimator", Icons.Default.Check, "prof"),
        Triple("Preventive Measures", Icons.Default.Warning, "prevent"),
        Triple("Ask Mkulima \uD83D\uDE02", Icons.Default.Face, "farmerbot"),
        Triple("Settings", Icons.Default.Settings, "set"),
        Triple("Setup Your Profile", Icons.Default.Person, "profile"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Welcome, Farmer 👨🏾‍🌾", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items) { (title, icon, route) ->
                    AnimatedCard(title, icon, onClick = {
                        navController.navigate(route)
                    })
                }
            }
        }
    }
}

@Composable
fun AnimatedCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (pressed) Color(0xFFD0E8FF) else Color.White,
        label = "CardColor"
    )
    val elevation by animateDpAsState(
        targetValue = if (pressed) 12.dp else 4.dp,
        label = "CardElevation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    pressed = true
                    onClick()
                },
                onClickLabel = "Navigate to $title"
            ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(elevation),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

