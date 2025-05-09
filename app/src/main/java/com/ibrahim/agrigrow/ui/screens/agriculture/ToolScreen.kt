package com.ibrahim.agrigrow.ui.screens.agriculture

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolScreen(navController: NavController) {
    val spacing = 12.dp

    // List of images for the top card
    val images = listOf(R.drawable.anime10, R.drawable.anime3) // Add your second image
    var currentImageIndex by remember { mutableStateOf(0) }
    val alpha by animateFloatAsState(targetValue = if (currentImageIndex == 0) 1f else 0f)

    // Change image every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Switch image every 3 seconds
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Know Your Tool", fontWeight = FontWeight.Bold)
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
            // Top Card with swapping images and fade effect
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
                        modifier = Modifier.fillMaxSize().alpha(alpha)
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // pest Description
            Text(
                text = "\uD83E\uDDF0 Proper maintenance of your farming tools is essential for efficient and productive work in the field. Clean and store tools after every use to prevent rust and wear, ensuring they last longer and perform at their best. \uD83E\uDDFC\uD83D\uDD27 Regular sharpening, oiling, and checking for damage helps avoid breakdowns during critical farming tasks, saving both time and money. ✅ A well-maintained tool is not just a piece of equipment — it's a partner in achieving healthy crops and higher yields \uD83C\uDF3E\uD83D\uDCAA.",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            // Card Text
            val pestInfoList = listOf(
                Pair(R.drawable.on1, "\uD83D\uDEE0\uFE0F Light farm tools like hoes, rakes, and hand trowels are essential for quick, small-scale tasks in the farm or garden. Always grip the tool firmly and use smooth, controlled movements to avoid strain or injury \uD83D\uDCAA. Keep the blades sharp and clean for easier work and better results ✨. After use, wash off any soil, dry them, and store in a cool, dry place to prevent rust \uD83E\uDDFC\uD83C\uDF3F."),
                Pair(R.drawable.on8, "\uD83D\uDE9C Heavy farm tools like tractors, ploughs, and harvesters are powerful machines designed for large-scale farming tasks. Before using them, always check fuel, oil, and tire pressure to ensure safe operation ⚙\uFE0F\uD83D\uDEE2\uFE0F. Follow safety guidelines, wear protective gear, and never operate without proper training \uD83D\uDC68\u200D\uD83C\uDF3E\uD83E\uDDBA. After use, clean and service the equipment regularly to keep it running smoothly and extend its lifespan \uD83D\uDD27✅.")
            )

            Column(modifier = Modifier.padding(16.dp)) {
                pestInfoList.forEach { (imageRes, description) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Image Card
                        Box(
                            modifier = Modifier
                                .width(160.dp)
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                                .shadow(4.dp, RoundedCornerShape(12.dp))
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = "Pest Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Text Description
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Text(
                                text = "Know Your Tool",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = description,
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Bottom Button
            Button(
                onClick = { navController.navigate("training") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(text = "Learn More on Tools", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
