package com.ibrahim.agrigrow.ui.screens.health

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController
import com.ibrahim.agrigrow.model.AnimalHealth
import com.ibrahim.agrigrow.viewmodel.AnimalHealthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalHealthScreen(
    viewModel: AnimalHealthViewModel,
    navController: NavController
) {
    var animalName by remember { mutableStateOf("") }
    var healthStatus by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var treatment by remember { mutableStateOf("") }

    val records by viewModel.healthRecords.collectAsState(initial = emptyList())
    val shape = RoundedCornerShape(16.dp)
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Animal Health Tracker", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )

        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF4CAF50)) {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 /* navController.navigate("home") */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
                    label = { Text("Home", color = Color.White) }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 /* navController.navigate("profile") */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Profile", color = Color.White) }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 /* navController.navigate("settings") */ },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) },
                    label = { Text("Settings", color = Color.White) }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AnimatedVisibility(visible = true, enter = fadeIn()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = animalName,
                        onValueChange = { animalName = it },
                        label = { Text("Animal Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape),
                        shape = shape,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = healthStatus,
                        onValueChange = { healthStatus = it },
                        label = { Text("Health Status") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape),
                        shape = shape,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Date") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape),
                        shape = shape,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = treatment,
                        onValueChange = { treatment = it },
                        label = { Text("Treatment") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape),
                        shape = shape,
                        singleLine = true
                    )
                }
            }

            Button(
                onClick = {
                    if (animalName.isNotBlank() && healthStatus.isNotBlank() && date.isNotBlank()) {
                        viewModel.addRecord(
                            AnimalHealth(
                                animalName = animalName,
                                healthStatus = healthStatus,
                                date = date,
                                treatment = treatment
                            )
                        )
                        animalName = ""
                        healthStatus = ""
                        date = ""
                        treatment = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Add Record", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Records",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 60.dp)
            ) {
                items(records) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Animal: ${record.animalName}", style = MaterialTheme.typography.titleSmall)
                            Text("Status: ${record.healthStatus}")
                            Text("Date: ${record.date}")
                            Text("Treatment: ${record.treatment}")
                            Spacer(modifier = Modifier.height(4.dp))
                            IconButton(onClick = { viewModel.deleteRecord(record) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}

