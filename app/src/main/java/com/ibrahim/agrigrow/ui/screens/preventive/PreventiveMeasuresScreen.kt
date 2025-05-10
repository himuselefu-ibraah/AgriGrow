package com.ibrahim.agrigrow.ui.screens.preventive

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class PreventiveCardData(
    val title: String,
    val description: String,
    val extraTips: String,
    val imageResId: Int,
    val fullDialogText: String? = null
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PreventiveMeasuresScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequesters = remember {
        (1..10).associateWith { BringIntoViewRequester() }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Preventive Measures", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF9F9F9))
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items((1..10).toList()) { week ->
                val requester = bringIntoViewRequesters[week]!!
                WeekContent(week = week, requester = requester, coroutineScope = coroutineScope)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeekContent(
    week: Int,
    requester: BringIntoViewRequester,
    coroutineScope: CoroutineScope
) {
    val cardDataList = when (week) {
        2 -> listOf(
            PreventiveCardData(
                title = "Use Organic Pesticides",
                description = "Use organic pesticides to reduce chemical exposure.",
                extraTips = "Apply weekly.\nMonitor effects closely.",
                imageResId = R.drawable.no3,
                fullDialogText = "Organic pesticides are eco-friendly alternatives to chemicals. They reduce environmental impact and help maintain soil health.\n\nExtra Tips:\n- Use neem or garlic sprays\n- Monitor pest levels weekly\n- Avoid overapplication"
            ),
            PreventiveCardData(
                title = "Crop Rotation",
                description = "Rotate crops regularly to prevent soil pests.",
                extraTips = "Rotate every season.\nImprove soil fertility.",
                imageResId = R.drawable.no3,
                fullDialogText = "Crop rotation interrupts pest cycles and enhances nutrient balance.\n\nExtra Tips:\n- Change crop family every season\n- Alternate root and leafy crops\n- Add legumes to restore nitrogen"
            ),

        )
        else -> listOf(
            PreventiveCardData(
                title = "General Prevention",
                description = "Learn about pest prevention and control strategies.",
                extraTips = "Always inspect plants.\nMaintain soil health.",
                imageResId = R.drawable.img_11,
                fullDialogText = "Effective pest prevention includes inspecting crops regularly, using barriers, and maintaining clean, fertile soil.\n\nExtra Tips:\n- Practice sanitation\n- Remove affected plants quickly\n- Encourage natural predators"
            )
        )
    }


    Column(modifier = Modifier.bringIntoViewRequester(requester)) {
        Text(
            text = "Week $week",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF2E7D32)
        )

        if (week == 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                cardDataList.forEach { cardData ->
                    PreventiveCard(
                        cardData = cardData,
                        coroutineScope = coroutineScope,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        } else {
            cardDataList.forEach { cardData ->
                PreventiveCard(
                    cardData = cardData,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }

        Divider(
            color = Color.Gray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun PreventiveCard(
    cardData: PreventiveCardData,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val scale = remember { Animatable(1f) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .shadow(6.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                coroutineScope.launch {
                    scale.animateTo(0.95f, tween(100))
                    delay(100)
                    scale.animateTo(1f, tween(100))
                }
            }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = cardData.imageResId),
            contentDescription = cardData.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .scale(scale.value)
                .clip(RoundedCornerShape(12.dp))
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = cardData.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF388E3C)
        )

        Text(
            text = cardData.description,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        TextButton(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Read More", color = Color(0xFF4CAF50))
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(cardData.title) },
            text = {
                Text(
                    cardData.fullDialogText
                        ?: "${cardData.description}\n\nExtra tips:\n${cardData.extraTips}"
                )
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}







