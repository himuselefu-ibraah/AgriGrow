package com.ibrahim.agrigrow.ui.screens.fertilizer

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
fun FertilizerScreen(navController: NavController) {
    val cardItems = listOf(
        R.drawable.new3,
        R.drawable.new2,
        R.drawable.new12,
        R.drawable.new4
    )

    val spacing = 12.dp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Fertilizer Guide", fontWeight = FontWeight.Bold)
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
            // Top Card with Auto-Switching Images
            val images = listOf(R.drawable.new11, R.drawable.new1)
            var imageIndex by remember { mutableStateOf(0) }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(3000)
                    imageIndex = (imageIndex + 1) % images.size
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Crossfade(targetState = imageIndex, animationSpec = tween(durationMillis = 1000)) { index ->
                    Image(
                        painter = painterResource(id = images[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Fertilizer Description
            Text(
                text = "\uD83C\uDF3E To use fertilizer the right way, start by testing your soil \uD83E\uDDEA to know what nutrients it needs. Choose the right type of fertilizer \uD83C\uDF3F for your specific crop, and always follow the recommended amount \uD83D\uDCCF—too much can harm your plants. Apply it during planting and at key growth stages ⏳, and make sure to water \uD83D\uDCA7 afterward so the nutrients can soak into the roots. Smart fertilizing leads to healthier crops and bigger harvests! \uD83E\uDD55\uD83C\uDF3D\uD83C\uDF45",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = "Correct Ways to Apply Fertilizer",
                fontSize = 22.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 4 Cards: 2 per row
            for (i in 0 until 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = spacing / 2),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    CustomImageCard(cardItems[i * 2], Modifier.weight(1f))
                    CustomImageCard(cardItems[i * 2 + 1], Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Bottom Button
            Button(
                onClick = { navController.navigate("fertilizer") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(text = "Learn More on Fertilizers", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun CustomImageCard(imageRes: Int, modifier: Modifier) {
    var clicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (clicked) 0.95f else 1f,
        label = "cardScale"
    )

    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .height(180.dp)
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
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


