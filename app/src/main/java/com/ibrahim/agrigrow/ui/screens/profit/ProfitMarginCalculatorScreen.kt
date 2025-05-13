package com.ibrahim.agrigrow.ui.screens.profit

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.data.ProfitMarginDatabaseHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfitMarginCalculatorScreen(navController: NavController) {
    var cropName by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var income by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dbHelper = ProfitMarginDatabaseHelper(context)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Calculate Your Profit Margin", fontWeight = FontWeight.Bold)
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
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(paddingValues)) {

            // Centered Image on Top
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

            // Crop Name Input
            OutlinedTextField(
                value = cropName,
                onValueChange = { cropName = it },
                label = { Text("Crop Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = shape
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cost Input
            OutlinedTextField(
                value = cost,
                onValueChange = { cost = it },
                label = { Text("Cost") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = shape,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Income Input
            OutlinedTextField(
                value = income,
                onValueChange = { income = it },
                label = { Text("Income") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = shape,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Calculate Button
            Button(
                onClick = {
                    val costValue = cost.toDoubleOrNull()
                    val incomeValue = income.toDoubleOrNull()

                    if (costValue != null && incomeValue != null) {
                        // Calculate profit margin
                        val profitMargin = (incomeValue - costValue) / incomeValue * 100
                        result = "Profit Margin: %.2f%%".format(profitMargin)

                        // Save to database
                        dbHelper.saveProfitMargin(cropName, costValue, incomeValue)
                    } else {
                        result = "Please enter valid cost and income values"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),  // For reduced rounded corners
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))

            ) {
                Text("Calculate", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display Result
            Text(
                text = result,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

