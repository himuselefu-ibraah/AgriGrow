package com.ibrahim.agrigrow.ui.screens.agriculture

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.ibrahim.agrigrow.navigation.ROUT_HOME
import com.ibrahim.agrigrow.navigation.ROUT_PROFILE
import com.ibrahim.agrigrow.navigation.ROUT_SETTINGS
import com.ibrahim.agrigrow.ui.theme.newgreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgricultureGuideScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedIndex by remember { mutableStateOf(0) }


    val guideSections = listOf(
        Triple("Soil Preparation", "Learn how to test and improve soil for maximum yield.", "https://youtu.be/8ulpy_GFLDk?si=vAf5Tk4f7q8R44iB"),
        Triple("Planting Techniques", "Best practices for seed spacing, depth, and timing.", "https://youtu.be/mgvztGVhN-E?si=wjSMixlzUHITMM3O"),
        Triple("Irrigation", "Efficient watering methods: drip, sprinkler, and manual systems.", "https://youtu.be/Z9HAy9EYKKs?si=WkAsLdxYKNUrmVkG"),
        Triple("Weed Management", "Controlling weeds naturally and chemically.", "https://youtu.be/QXeupnisDck?si=ks1JplcLxGinO3bC"),
        Triple("Fertilizer Application", "Organic and inorganic fertilizer use guidelines.", "https://youtu.be/4vKNnIZWtwM"),
        Triple("Pest & Disease Control", "Identify and control common threats to crops.", "https://youtu.be/0D--ltYWKXI?si=b4Jo1cUQyRdW5LAl"),
        Triple("Harvesting Techniques", "When and how to harvest for quality and quantity.", "https://youtu.be/kWd_QnyO3eI?si=_VzMbGNDLcceHANd"),
        Triple("Post-Harvest Handling", "Storage, sorting, packaging, and preservation tips.", "https://youtu.be/hAJnRAiMNuA?si=zCos6MWo9D7diHxC"),
        Triple("Organic vs. Inorganic Farming", "Differences, benefits, and which to choose.", "https://youtu.be/8HUnW8IChv0"),
        Triple("Market Trends & Prices", "How to analyze prices and sell your produce smartly.", "https://youtu.be/XkJ7mP7uaTQ?t=38")
    )

    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Agriculture Guide", fontWeight = FontWeight.Bold)
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
                    icon = { Icon(Icons.Default.Settings, contentDescription = "set") },
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
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            guideSections.forEach { (title, description, url) ->
                item {
                    SectionCardWithLink(title, description, url, context)
                }
            }

        }
    }
}

@Composable
fun SectionCardWithLink(title: String, description: String, url: String, context: android.content.Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                // Open the YouTube video in a browser or YouTube app
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(context, intent, null)
            },

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(12.dp)

            ) {
                Text("Learn More")
            }
        }
    }
}
