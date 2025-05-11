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
import com.ibrahim.agrigrow.data.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { ProfileDatabaseHelper(context) }

    val profileImages = listOf(R.drawable.fm1, R.drawable.fm2, R.drawable.fm3)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedImageId by remember { mutableStateOf(0) }
    var savedProfile by remember { mutableStateOf<UserProfile?>(null) }

    // Load existing profile once
    LaunchedEffect(Unit) {
        savedProfile = dbHelper.getProfile()
        savedProfile?.let {
            name = it.name
            email = it.email
            phone = it.phone
            selectedImageId = it.imageId
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile Setup", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Your Saved Profile", style = MaterialTheme.typography.titleLarge)

            savedProfile?.let { profile ->
                Image(
                    painter = painterResource(id = profile.imageId),
                    contentDescription = "Saved Profile Pic",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Green, CircleShape)
                )
                Text("Name: ${profile.name}")
                Text("Email: ${profile.email}")
                Text("Phone: ${profile.phone}")
            } ?: Text("No profile saved yet.")

            Divider(thickness = 1.dp)

            Text("Update Profile", style = MaterialTheme.typography.titleMedium)

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                profileImages.forEach { imageId ->
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Profile Pic",
                        modifier = Modifier
                            .size(70.dp)
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
                        dbHelper.saveOrUpdateProfile(name, email, phone, selectedImageId)
                        savedProfile = dbHelper.getProfile() // Immediately refresh view
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











