package com.ibrahim.agrigrow.ui.screens.pest

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PestScreen(navController: NavController) {
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
                    Text("Pest and Diseases", fontWeight = FontWeight.Bold)
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
                        painter = painterResource(id = R.drawable.new11),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // pest Description
            Text(
                text = "To prevent pests \uD83D\uDC1B, rotate crops \uD83C\uDF31, keep the farm clean \uD83E\uDDF9, and attract natural predators \uD83D\uDC1E. Use organic sprays like neem oil \uD83C\uDF3F, avoid overwatering \uD83D\uDEB1, and space plants well \uD83D\uDCCF. Store harvests properly \uD83E\uDDFA and check crops often \uD83D\uDC40 to catch pests early.",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Card 1: Image + Text (Split)
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .shadow(4.dp, RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_11),
                            contentDescription = "Pest Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Regularly controlling pests helps protect your crops from damage, ensuring a healthier harvest.\n" +
                                        "\nHealthy crops free from pests produce better yields.",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                        }
                    }
                }

                // Card 2: Full Image
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .shadow(4.dp, RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.no3), // Replace with another image
                        contentDescription = "Full Image Card",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }


            Text(
                text = "Don't Allow Pests to Control",
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
                onClick = { navController.navigate("pest")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(text = "Learn More on Pests", fontSize = 18.sp, color = Color.White)
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


