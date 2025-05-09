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
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.navigation.ROUT_HOME
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
    ComplexFarmTool(1, "Rotavator", "Used to prepare land by breaking up soil.", "https://www.youtube.com/watch?v=VIDEO_ID1"),
    ComplexFarmTool(2, "Seed Drill", "Ensures even distribution of seeds during planting.", "https://www.youtube.com/watch?v=VIDEO_ID2"),
    ComplexFarmTool(3, "Power Weeder", "Removes weeds efficiently from between crops.", "https://www.youtube.com/watch?v=VIDEO_ID3"),
    ComplexFarmTool(4, "Combine Harvester", "Performs reaping, threshing, and winnowing.", "https://www.youtube.com/watch?v=VIDEO_ID4"),
    ComplexFarmTool(5, "Laser Land Leveler", "Levels the field precisely for uniform irrigation.", "https://www.youtube.com/watch?v=VIDEO_ID5"),
    ComplexFarmTool(6, "Drone Sprayer", "Sprays pesticides or fertilizers using a drone.", "https://www.youtube.com/watch?v=VIDEO_ID6"),
    ComplexFarmTool(7, "Automatic Milking Machine", "Milks cows automatically with hygiene and efficiency.", "https://www.youtube.com/watch?v=VIDEO_ID7"),
    ComplexFarmTool(8, "Soil Moisture Sensor", "Detects moisture content in soil to optimize irrigation.", "https://www.youtube.com/watch?v=VIDEO_ID8"),
    ComplexFarmTool(9, "Tractor", "Used for plowing, tilling, and hauling.", "https://www.youtube.com/watch?v=VIDEO_ID9"),
    ComplexFarmTool(10, "Plough", "Turns over the soil to prepare for planting.", "https://www.youtube.com/watch?v=VIDEO_ID10"),
    ComplexFarmTool(11, "Disc Harrow", "Breaks up clumps and surface crusts.", "https://www.youtube.com/watch?v=VIDEO_ID11"),
    ComplexFarmTool(12, "Cultivator", "Loosens the soil and removes weeds.", "https://www.youtube.com/watch?v=VIDEO_ID12"),
    ComplexFarmTool(13, "Threshing Machine", "Separates grain from stalks.", "https://www.youtube.com/watch?v=VIDEO_ID13"),
    ComplexFarmTool(14, "Hay Baler", "Compresses hay into compact bales.", "https://www.youtube.com/watch?v=VIDEO_ID14"),
    ComplexFarmTool(15, "Irrigation Pump", "Distributes water to crops efficiently.", "https://www.youtube.com/watch?v=VIDEO_ID15"),
    ComplexFarmTool(16, "Sprinkler System", "Irrigates crops by spraying water.", "https://www.youtube.com/watch?v=VIDEO_ID16"),
    ComplexFarmTool(17, "Drip Irrigation Kit", "Provides water directly to roots.", "https://www.youtube.com/watch?v=VIDEO_ID17"),
    ComplexFarmTool(18, "Fertilizer Spreader", "Distributes fertilizers evenly.", "https://www.youtube.com/watch?v=VIDEO_ID18"),
    ComplexFarmTool(19, "Hand Seeder", "Manual seed distribution tool.", "https://www.youtube.com/watch?v=VIDEO_ID19"),
    ComplexFarmTool(20, "Wheelbarrow", "Transports materials around the farm.", "https://www.youtube.com/watch?v=VIDEO_ID20"),
    ComplexFarmTool(21, "Greenhouse Tunnel", "Controlled environment for growing crops.", "https://www.youtube.com/watch?v=VIDEO_ID21"),
    ComplexFarmTool(22, "Solar Dryer", "Used to dry produce using solar energy.", "https://www.youtube.com/watch?v=VIDEO_ID22"),
    ComplexFarmTool(23, "Hand Sprayer", "Manually sprays pesticides and herbicides.", "https://www.youtube.com/watch?v=VIDEO_ID23"),
    ComplexFarmTool(24, "Boom Sprayer", "Large-scale pesticide application.", "https://www.youtube.com/watch?v=VIDEO_ID24"),
    ComplexFarmTool(25, "Knapsack Sprayer", "Portable sprayer for small farms.", "https://www.youtube.com/watch?v=VIDEO_ID25"),
    ComplexFarmTool(26, "Soil pH Meter", "Measures soil acidity levels.", "https://www.youtube.com/watch?v=VIDEO_ID26"),
    ComplexFarmTool(27, "Hydroponic Tower", "Grows crops in vertical water systems.", "https://www.youtube.com/watch?v=VIDEO_ID27"),
    ComplexFarmTool(28, "Aquaponic System", "Combines fish farming with hydroponics.", "https://www.youtube.com/watch?v=VIDEO_ID28"),
    ComplexFarmTool(29, "Seed Germinator", "Provides ideal conditions for seed germination.", "https://www.youtube.com/watch?v=VIDEO_ID29"),
    ComplexFarmTool(30, "Compost Turner", "Turns compost for faster decomposition.", "https://www.youtube.com/watch?v=VIDEO_ID30"),
    ComplexFarmTool(31, "Chaff Cutter", "Cuts straw and hay into small pieces.", "https://www.youtube.com/watch?v=VIDEO_ID31"),
    ComplexFarmTool(32, "Grain Storage Bin", "Stores harvested grains safely.", "https://www.youtube.com/watch?v=VIDEO_ID32"),
    ComplexFarmTool(33, "Electric Fence Energizer", "Protects crops and livestock with electric fencing.", "https://www.youtube.com/watch?v=VIDEO_ID33"),
    ComplexFarmTool(34, "Bee Hive Box", "Used for beekeeping and honey collection.", "https://www.youtube.com/watch?v=VIDEO_ID34"),
    ComplexFarmTool(35, "Egg Incubator", "Hatches eggs by providing heat and rotation.", "https://www.youtube.com/watch?v=VIDEO_ID35"),
    ComplexFarmTool(36, "Feed Mixer", "Blends feed ingredients evenly.", "https://www.youtube.com/watch?v=VIDEO_ID36"),
    ComplexFarmTool(37, "Milking Parlor", "Facility for milking multiple cows.", "https://www.youtube.com/watch?v=VIDEO_ID37"),
    ComplexFarmTool(38, "Cattle Crush", "Restrains cattle for medical or grooming.", "https://www.youtube.com/watch?v=VIDEO_ID38"),
    ComplexFarmTool(39, "Portable Soil Tester", "Quick soil testing in the field.", "https://www.youtube.com/watch?v=VIDEO_ID39"),
    ComplexFarmTool(40, "GPS Field Mapping Device", "Maps farmland with GPS technology.", "https://www.youtube.com/watch?v=VIDEO_ID40")
)

// Main Composable Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgroToolsTrainingScreen() {
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
            TopAppBar(
                title = { Text("Tool Guide") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = newgreen,
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
                       // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        //  navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        //  navController.navigate(ROUT_HOME)
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

                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
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
