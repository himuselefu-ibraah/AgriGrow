package com.ibrahim.agrigrow.ui.screens.cropcalendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.navigation.ROUT_HOME
import com.ibrahim.agrigrow.navigation.ROUT_PROFILE
import com.ibrahim.agrigrow.navigation.ROUT_SETTINGS
import com.ibrahim.agrigrow.ui.theme.newgreen
import com.ibrahim.agrigrow.viewmodel.CropCalendarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropCalendarScreen(viewModel: CropCalendarViewModel, navController: NavController) {
    val cropList by viewModel.cropList.collectAsState()
    val query by viewModel.query.collectAsState()
    var selectedIndex by remember { mutableStateOf(0) }


    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Crop Calendar", fontWeight = FontWeight.Bold)
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
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                          navController.navigate(ROUT_SETTINGS)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("set") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_PROFILE)
                    }
                )

            }
        },

        ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // fixed height to avoid stretching
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anime10), // your drawable
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize() // fills entire card
                )
            }



            OutlinedTextField(
                value = query,
                onValueChange = { viewModel.onQueryChanged(it) },
                label = { Text("Search crop or region") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(cropList) { crop ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                // You can navigate to details screen here
                                navController.navigate("crop_detail/${crop.id}")
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = crop.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Region: ${crop.region}")
                            Text(text = "Planting: ${crop.plantingMonth}")
                            Text(text = "Harvesting: ${crop.harvestingMonth}")
                        }
                    }
                }
            }
        }
    }
}

