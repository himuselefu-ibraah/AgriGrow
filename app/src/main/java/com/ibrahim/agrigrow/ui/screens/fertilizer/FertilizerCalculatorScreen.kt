package com.ibrahim.agrigrow.ui.screens.fertilizer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.data.FertilizerDatabaseHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FertilizerCalculatorScreen(navController: NavController) {
    var crop by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dbHelper = FertilizerDatabaseHelper(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fertilizer Calculator") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White // Green color for the back arrow
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50), // Green color for the top bar
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(paddingValues)) {

            //  Centered Image on Top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nim), // Put your actual drawable here
                    contentDescription = "Fertilizer Image",
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                )
            }


            // Crop Type Input
            OutlinedTextField(
                value = crop,
                onValueChange = { crop = it },
                label = { Text("Crop Type") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Farm Size Input
            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Farm Size (acres)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Calculate Button
            Button(
                onClick = {
                    // Handle Fertilizer Calculation
                    val fertilizerAmount = area.toDoubleOrNull()?.times(50)
                    result = if (fertilizerAmount != null) {
                        val resultText = "Apply $fertilizerAmount kg of fertilizer for $crop."
                        // Save calculation to database
                        dbHelper.saveCalculation(crop, area, resultText)
                        resultText
                    } else {
                        "Enter valid area."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Green button color
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text("Calculate", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Result Display
            Text(
                text = result,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



