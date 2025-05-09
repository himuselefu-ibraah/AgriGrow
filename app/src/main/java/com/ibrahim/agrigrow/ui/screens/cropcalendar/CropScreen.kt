package com.ibrahim.agrigrow.ui.screens.cropcalendar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropScreen(navController: NavController) {
    val cardItems = listOf(
        R.drawable.on2,
        R.drawable.on3,
        R.drawable.on4,
        R.drawable.no1
    )

    val spacing = 12.dp

    Scaffold(
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Makes content scrollable
        ) {
            // Top Card with Swapping Images (with smooth fade)
            val images = listOf(R.drawable.anime10, R.drawable.he1) // Add your second image
            var currentImageIndex by remember { mutableStateOf(0) }
            val alpha by animateFloatAsState(targetValue = if (currentImageIndex == 0) 1f else 0f)

            LaunchedEffect(Unit) {
                while (true) {
                    delay(3000) // Switch image every 3 seconds
                    currentImageIndex = (currentImageIndex + 1) % images.size
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = images[currentImageIndex]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(alpha) // Apply fade animation
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Crop Description
            Text(
                text = "\uD83C\uDF31 Planting crops wisely and within the right timelines is key to achieving healthy growth and high yields. \uD83D\uDDD3\uFE0F Farmers should follow seasonal calendars and consider local climate patterns to choose the best time for sowing. \uD83C\uDF26\uFE0F Early or late planting can lead to crop failure, pest attacks, or low productivity. \uD83D\uDCCA Planning ahead with proper spacing, soil preparation, and timely planting ensures better harvests and increased profits. ✅",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // First Card (Normal position)
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray)
                            .shadow(4.dp, RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.he2),
                            contentDescription = "Light Tool",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Second Card (Offset lower)
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(160.dp)
                            .offset(y = 16.dp) // Makes the second card appear lower
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray)
                            .shadow(4.dp, RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.he5),
                            contentDescription = "Heavy Tool",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Descriptions below both cards
                Text(
                    text = "\uD83C\uDF3E Plant your crops at the right time to match the rains and growing seasons. \uD83D\uDDD3\uFE0F Timely planting boosts growth, prevents pests, and increases yields. \uD83C\uDF31 Always follow your local crop calendar for best results. \uD83D\uDCC8 Smart timing means healthier crops and better harvests! ✅",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "\uD83D\uDD70\uFE0F⛅ Timing is everything, farmer! \uD83C\uDF31 Sow your seeds when the rains are just right and the soil is ready. \uD83C\uDF3E Smart planting = stronger crops, fewer pests, and bigger harvests. \uD83C\uDFAF Follow the seasons, trust the calendar, and grow like a pro! \uD83D\uDCAA\uD83D\uDCC5✨",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(spacing))

            // Bottom Button
            Button(
                onClick = { navController.navigate("crop") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(text = "Know Your Crop Calendar", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
