package com.ibrahim.agrigrow.ui.screens.splash

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onGetStartedClick: () -> Unit) {
    val images = listOf(
        R.drawable.anime10,
        R.drawable.anime2,
        R.drawable.anime3,
        R.drawable.anime4
    )
    var currentImageIndex by remember { mutableStateOf(0) }

    // Auto-slide every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = "fade"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
            .alpha(alphaAnim),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "AgriGrow",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Crossfade(targetState = currentImageIndex, animationSpec = tween(1000)) { index ->
                    Image(
                        painter = painterResource(id = images[index]),
                        contentDescription = "Slideshow Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome to AgriGrow",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onGetStartedClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Start Your Green Journey",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


