package com.ibrahim.agrigrow.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.data.ProfileDatabaseHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { ProfileDatabaseHelper(context) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedImageId by remember { mutableStateOf(0) }

    val profileImages = listOf(
        R.drawable.new2,
        R.drawable.new1,
        R.drawable.new3
    )

    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Profile Setup", fontWeight = FontWeight.Bold)
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Choose Profile Picture", style = MaterialTheme.typography.titleLarge)

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                profileImages.forEach { imageId: Int ->
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Profile Pic",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(
                                3.dp,
                                if (selectedImageId == imageId) Color.Green else Color.Gray,
                                CircleShape
                            )
                            .clickable { selectedImageId = imageId }
                    )
                }
            }

            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })

            Button(
                onClick = {
                    if (selectedImageId != 0) {
                        dbHelper.saveProfile(name, email, phone, selectedImageId)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Profile", color = Color.White)
            }
        }
    }
}









