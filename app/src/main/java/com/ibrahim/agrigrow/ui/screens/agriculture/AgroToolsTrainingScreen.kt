package com.ibrahim.agrigrow.ui.screens.agriculture

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.navigation.ROUT_HOME
import com.ibrahim.agrigrow.navigation.ROUT_PROFILE
import com.ibrahim.agrigrow.navigation.ROUT_SETTINGS
import com.ibrahim.agrigrow.ui.theme.newgreen

// Data model
data class ComplexFarmTool(
    val id: Int,
    val name: String,
    val usage: String,
    val learnMoreUrl: String
)

// List of 40+ named complex farm tools
val complexFarmTools = listOf(
    ComplexFarmTool(1, "Rotavator", "Used to prepare land by breaking up soil.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(2, "Seed Drill", "Ensures even distribution of seeds during planting.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(3, "Power Weeder", "Removes weeds efficiently from between crops.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(4, "Combine Harvester", "Performs reaping, threshing, and winnowing.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(5, "Laser Land Leveler", "Levels the field precisely for uniform irrigation.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(6, "Drone Sprayer", "Sprays pesticides or fertilizers using a drone.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(7, "Automatic Milking Machine", "Milks cows automatically with hygiene and efficiency.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(8, "Soil Moisture Sensor", "Detects moisture content in soil to optimize irrigation.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(9, "Tractor", "Used for plowing, tilling, and hauling.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(10, "Plough", "Turns over the soil to prepare for planting.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(11, "Disc Harrow", "Breaks up clumps and surface crusts.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(12, "Cultivator", "Loosens the soil and removes weeds.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(13, "Threshing Machine", "Separates grain from stalks.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(14, "Hay Baler", "Compresses hay into compact bales.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(15, "Irrigation Pump", "Distributes water to crops efficiently.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(16, "Sprinkler System", "Irrigates crops by spraying water.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(17, "Drip Irrigation Kit", "Provides water directly to roots.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(18, "Fertilizer Spreader", "Distributes fertilizers evenly.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(19, "Hand Seeder", "Manual seed distribution tool.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(20, "Wheelbarrow", "Transports materials around the farm.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(21, "Greenhouse Tunnel", "Controlled environment for growing crops.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(22, "Solar Dryer", "Used to dry produce using solar energy.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(23, "Hand Sprayer", "Manually sprays pesticides and herbicides.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(24, "Boom Sprayer", "Large-scale pesticide application.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(25, "Knapsack Sprayer", "Portable sprayer for small farms.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(26, "Soil pH Meter", "Measures soil acidity levels.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(27, "Hydroponic Tower", "Grows crops in vertical water systems.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(28, "Aquaponic System", "Combines fish farming with hydroponics.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(29, "Seed Germinator", "Provides ideal conditions for seed germination.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(30, "Compost Turner", "Turns compost for faster decomposition.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(31, "Chaff Cutter", "Cuts straw and hay into small pieces.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(32, "Grain Storage Bin", "Stores harvested grains safely.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(33, "Electric Fence Energizer", "Protects crops and livestock with electric fencing.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(34, "Bee Hive Box", "Used for beekeeping and honey collection.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(35, "Egg Incubator", "Hatches eggs by providing heat and rotation.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(36, "Feed Mixer", "Blends feed ingredients evenly.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(37, "Milking Parlor", "Facility for milking multiple cows.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(38, "Cattle Crush", "Restrains cattle for medical or grooming.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(39, "Portable Soil Tester", "Quick soil testing in the field.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk"),
    ComplexFarmTool(40, "GPS Field Mapping Device", "Maps farmland with GPS technology.", "https://youtu.be/stxMF5QNugY?si=E38E6rwcd_8-tjUk")
)

// Main Composable Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgroToolsTrainingScreen(navController: NavController) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(0) }


    val filteredTools = complexFarmTools.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.usage.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Tool Guide", fontWeight = FontWeight.Bold)
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

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = newgreen
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Favorites") },
                    label = { Text("Settings") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                          navController.navigate(ROUT_SETTINGS)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_PROFILE)
                    }
                )

            }
        },
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)// fixed height to avoid stretching
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anime2), // your drawable
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize() // fills entire card
                )
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Tools") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(filteredTools) { tool ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(tool.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(tool.usage, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tool.learnMoreUrl))
                                context.startActivity(intent)
                            },

                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                                shape = RoundedCornerShape(12.dp)

                            ) {
                                Text("Learn More")
                            }
                        }
                    }
                }
            }
        }
    }
}
