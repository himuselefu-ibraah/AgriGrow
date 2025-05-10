package com.ibrahim.agrigrow.ui.screens.weatherupdates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.ui.theme.newgreen
import com.ibrahim.agrigrow.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val weather by viewModel.weather.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var city by remember { mutableStateOf("") }
    val context = LocalContext.current
    val apiKey = "ee0af368b764a9490b851478d6f0ad2c" // Replace with your actual OpenWeather API key
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Weather Updates") },
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


    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // fixed height to avoid stretching
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.weather), // your drawable
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize() // fills entire card
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city",color = Color.Green) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    cursorColor = Color.Green
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchWeather(city, apiKey) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Get Weather")
            }

            Spacer(modifier = Modifier.height(24.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator()
                }

                error != null -> {
                    Text(text = error ?: "Unknown error", color = MaterialTheme.colorScheme.error)
                }

                weather != null -> {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text("City: ${weather!!.name}", style = MaterialTheme.typography.titleLarge)
                        Text("Temperature: ${weather!!.main.temp} °C", style = MaterialTheme.typography.bodyLarge)
                        Text("Humidity: ${weather!!.main.humidity} %", style = MaterialTheme.typography.bodyLarge)
                        Text("Rainfall: ${weather!!.rain?.`1h` ?: "No Data"} mm", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}








