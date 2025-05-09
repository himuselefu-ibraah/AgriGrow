package com.ibrahim.agrigrow.ui.screens.starter


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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

data class FertilizerCardData(val imageRes: Int, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScreen(navController: NavController) {
    val cardItems = listOf(
        FertilizerCardData(R.drawable.new3, "Broadcasting method spreads fertilizer over entire field."),
        FertilizerCardData(R.drawable.new2, "Band placement puts fertilizer near seed rows."),
        FertilizerCardData(R.drawable.new12, "Side dressing is applied beside growing plants."),
        FertilizerCardData(R.drawable.new4, "Foliar feeding sprays nutrients directly on leaves.")
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
                .verticalScroll(rememberScrollState())
        ) {
            // Top Image Card
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

            // Fertilizer Text Description
            Text(
                text = "\uD83C\uDF3E To use fertilizer the right way, start by testing your soil \uD83E\uDDEA to know what nutrients it needs. Choose the right type of fertilizer \uD83C\uDF3F for your specific crop, and always follow the recommended amount \uD83D\uDCCF—too much can harm your plants. Apply it during planting and at key growth stages ⏳, and make sure to water \uD83D\uDCA7 afterward so the nutrients can soak into the roots. Smart fertilizing leads to healthier crops and bigger harvests! \uD83E\uDD55\uD83C\uDF3D\uD83C\uDF45",
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = "Common Fertilizer Methods",
                fontSize = 20.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Horizontally scrollable cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                cardItems.forEach { item ->
                    FertilizerMethodCard(item)
                }
            }

            Spacer(modifier = Modifier.height(spacing))

            // Learn More Button
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
fun FertilizerMethodCard(data: FertilizerCardData) {
    var clicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (clicked) 0.95f else 1f,
        label = "cardScale"
    )
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(220.dp)
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
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = data.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
            )
        }
    }
}


