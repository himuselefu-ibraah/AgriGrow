package com.ibrahim.agrigrow.ui.screens.health

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalScreen(navController: NavController) {
    val cardItems = listOf(
        R.drawable.ani3,
        R.drawable.ani1,
        R.drawable.ani4,
        R.drawable.ani2
    )

    val spacing = 12.dp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Animal Health", fontWeight = FontWeight.Bold)
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
            // Top Card with Image
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
                        painter = painterResource(id = R.drawable.ani5),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // animal Description
            Text(
                text = "\uD83D\uDC04\uD83E\uDE7A Animal Health Tip: Keep your animals healthy by providing clean water \uD83D\uDCA7, balanced nutrition \uD83E\uDD55, regular vaccinations \uD83D\uDC89, and a clean shelter \uD83C\uDFE0. Watch for signs of illness like loss of appetite or unusual behavior \uD83D\uDC40, and call a vet if needed \uD83D\uDE91. Healthy animals mean a productive farm! \uD83C\uDF3E✅",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = "\"\uD83D\uDC68\u200D⚕\uFE0F A Healthy Herd Starts with a Vet!\"",
                fontSize = 22.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                // Left (slightly higher) card
                Card(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(end = 60.dp)
                        .fillMaxWidth(0.55f)
                        .height(170.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hel1), // change to your fertilizer image
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Right (slightly lower) card
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(start = 60.dp)
                        .fillMaxWidth(0.55f)
                        .height(170.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hel2), // change to your fertilizer image
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "A veterinarian plays a key role in keeping your animals healthy. \uD83D\uDC04\uD83E\uDE7A They help detect diseases early and provide the right treatment. \uD83D\uDC89 Regular checkups prevent serious health issues and losses. ✅ When in doubt, always call a vet for professional advice. \uD83D\uDCDE\uD83D\uDC3E",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Veterinary Care: Essential for Livestock Health",
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
                onClick = { navController.navigate("animal_health")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(text = "Track Your Animal Health", fontSize = 18.sp, color = Color.White)
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
                    kotlinx.coroutines.delay(120)
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