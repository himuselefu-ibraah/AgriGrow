package com.ibrahim.agrigrow.ui.screens.irrigation

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class FertilizerItem(val imageRes: Int, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrrigationScreen(navController: NavController) {
    val images = listOf(
        R.drawable.new11, // Image 1
        R.drawable.irr3,  // Image 2
        R.drawable.irr4   // Image 3
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    // Periodically change the image every 3 seconds
    LaunchedEffect(key1 = currentImageIndex) {
        while (true) {
            delay(3000) // Delay in milliseconds
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    // Sample data for the cards
    val cardItems = listOf(
        FertilizerItem(imageRes = R.drawable.irr1, description = "Drip Irrigation: Efficient water delivery directly to roots."),
        FertilizerItem(imageRes = R.drawable.irr2, description = "Sprinkler Irrigation: Suitable for a wide variety of crops."),
        FertilizerItem(imageRes = R.drawable.irr3, description = "Surface Irrigation: Best for flat and level fields."),
        FertilizerItem(imageRes = R.drawable.irr4, description = "Subsurface Irrigation: Ideal for deep-rooted crops.")
    )

    val spacing = 12.dp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Irrigation Guide", fontWeight = FontWeight.Bold)
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
                .verticalScroll(rememberScrollState())
        ) {
            // Full Screen Image Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f), // Set the height of the image to take up 40% of the screen height
                shape = RoundedCornerShape(0.dp), // No corner radius for full screen effect
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Ensures image covers the full area without distortion
                    modifier = Modifier.fillMaxSize() // Fill the entire space available
                )
            }

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = "\uD83D\uDCA7 Don’t let your crops dry out—irrigation is the key to consistent growth and healthy yields! \uD83C\uDF31 Whether rain fails or shines, watering your farm ensures plants get the moisture they need. \uD83D\uDEBF Smart irrigation methods save water, boost productivity, and keep your harvests strong. \uD83C\uDF3E Keep your soil alive—irrigate wisely and grow with confidence! ✅",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = "Ways To Irrigate Your Farm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Two cards side by side in each row
            for (i in cardItems.indices step 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spacing),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    FertilizerCard(data = cardItems[i], modifier = Modifier.weight(1f))
                    if (i + 1 < cardItems.size) {
                        FertilizerCard(data = cardItems[i + 1], modifier = Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f)) // Fill empty space if odd number
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Learn More Button
            val context = LocalContext.current

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/shorts/f-VTieMMFbk?si=Xl5x1kApUV7meXb9"))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text("Watch Irrigation Guide", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun FertilizerCard(data: FertilizerItem, modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (clicked) 0.95f else 1f,
        label = "scaleAnim"
    )
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .height(240.dp)
            .scale(scale)
            .clickable {
                clicked = true
                coroutineScope.launch {
                    delay(120)
                    clicked = false
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = data.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

