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
    val staticImageRes = R.drawable.new11 // Static full-screen image

    val cardItems = listOf(
        FertilizerItem(imageRes = R.drawable.irr3, description = "Drip Irrigation: Efficient water delivery directly to roots."),
        FertilizerItem(imageRes = R.drawable.irr4, description = "Sprinkler Irrigation: Suitable for a wide variety of crops."),
        FertilizerItem(imageRes = R.drawable.irr3, description = "Surface Irrigation: Best for flat and level fields."),
        FertilizerItem(imageRes = R.drawable.irr1, description = "Subsurface Irrigation: Ideal for deep-rooted crops."),
        FertilizerItem(imageRes = R.drawable.irr7, description = "Center Pivot Irrigation: Automated, ideal for large-scale farming."),
        FertilizerItem(imageRes = R.drawable.irr5, description = "Furrow Irrigation: Water flows through shallow channels between crop rows."),
        FertilizerItem(imageRes = R.drawable.irr8, description = "Manual Irrigation: Traditional method using buckets or hoses."),
        FertilizerItem(imageRes = R.drawable.irr6, description = "Basin Irrigation: Involves flooding flat fields divided into basins."),
                FertilizerItem(imageRes = R.drawable.irr2, description = "Lateral Move Irrigation: Moves across the field, distributing water evenly."),
                FertilizerItem(imageRes = R.drawable.micro, description = "Micro irrigation delivers water in small, precise amounts using specialized systems, improving efficiency and reducing waste.")


    )

    val spacing = 16.dp

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
                .verticalScroll(rememberScrollState())
        ) {
            // Static full-screen image card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp), // Adjusted for a balanced header
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            ) {
                Image(
                    painter = painterResource(id = staticImageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(spacing))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "\uD83D\uDCA7 Don’t let your crops dry out—irrigation is the key to consistent growth and healthy yields! \uD83C\uDF31 Whether rain fails or shines, watering your farm ensures plants get the moisture they need. \uD83D\uDEBF Smart irrigation methods save water, boost productivity, and keep your harvests strong. \uD83C\uDF3E Keep your soil alive—irrigate wisely and grow with confidence! ✅",
                    fontSize = 16.sp,
                    color = Color(0xFF555555),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(spacing))

                Text(
                    text = "Ways To Irrigate Your Farm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Two cards per row
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
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(spacing))

                // Watch button
                val context = LocalContext.current

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Z9HAy9EYKKs?si=WkAsLdxYKNUrmVkG"))
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

            Spacer(modifier = Modifier.height(24.dp))
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
        Column {
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
