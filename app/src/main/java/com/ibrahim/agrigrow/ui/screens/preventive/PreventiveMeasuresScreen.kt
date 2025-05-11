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
    // Dynamically set card data based on the week
    val cardDataList = when (week) {
        1 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Leaf hoppers in your plants",
                extraTips = "Use Insecticidal Soap or Neem Oil.\nIntroduce Natural Predators.\n" +
                        "Remove Weeds and Debris.",
                imageResId = R.drawable.hopper,
                fullDialogText = " Leaf hoppers are small, winged insects that feed on plant sap by piercing plant cells with their needle-like mouthparts. They are often green or yellow and can cause significant damage to crops by causing leaf curling, yellowing, and stunted growth."
            )
        )
        2 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Aphids in your plants",
                extraTips = "Encourage Natural Predators.\nMonitor effects closely.\n" +
                        "Use Reflective Mulch or Barriers",
                imageResId = R.drawable.aphid,
                fullDialogText = "To prevent aphids, encourage natural predators like ladybugs and lacewings, which feed on them. Regularly inspect plants for aphid infestations and use neem oil or insecticidal soap as a treatment if needed."
            ),
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Leaf miners in your plants.",
                extraTips = "Remove Affected Leaves.\nIntroduce Beneficial Insects.\n" +
                        "Use Row Covers.",
                imageResId = R.drawable.miner,
                fullDialogText = "To prevent leaf miners \uD83E\uDEB2, regularly inspect leaves and remove any that show winding trails or blotchy patterns \uD83C\uDF43. Use neem oil or insecticidal soap to disrupt their life cycle naturally \uD83E\uDDF4. Encourage beneficial insects like parasitic wasps \uD83D\uDC1D that prey on leaf miner larvae."
            )
        )
        3 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Bean common Mosaic virus on your Plants",
                extraTips = "Use Virus-Free Seeds \uD83C\uDF31.\nRemove Infected Plants ❌.\n" +
                        "Practice Crop Rotation.",
                imageResId = R.drawable.mosa,
                fullDialogText = "Bean Mosaic Virus is a plant disease that causes mottled yellow-green patterns on leaves, stunted growth, and reduced yields. It is primarily spread by aphids and through infected seeds."
            )
        )
        4 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Flea beetles in your Crops.",
                extraTips = "Apply Diatomaceous Earth \uD83C\uDF3E.\nKeep Garden Clean \uD83E\uDDF9.\n" +
                        "Plant Trap Crops .",
                imageResId = R.drawable.flee,
                fullDialogText = "Flea beetles are small, jumping insects that chew tiny holes in leaves, often damaging young plants. To prevent them, use row covers, keep the garden clean, and apply natural deterrents like diatomaceous earth"
            )
        )
        5 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent Cercorspora leaf spot in your plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.spora,
                fullDialogText = "Cercospora Leaf Spot is a fungal disease that causes small, dark spots on leaves, leading to yellowing and early leaf drop. It spreads quickly in warm, humid conditions, especially with overhead watering. To prevent it, use disease-free seeds, water at the base, and remove infected leaves promptly"
            )
        )
        6 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent anthracnose in your plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.anthracnose,
                fullDialogText = "Anthracnose is a fungal disease that causes dark, sunken spots on leaves, stems, and fruits, often leading to plant wilting or dieback. It thrives in wet, humid conditions and spreads through splashing water and infected debris. To prevent it, use resistant varieties, avoid overhead watering, and remove infected plant parts quickly."
            )
        )
        7 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent blister beetles in your plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.bl,
                fullDialogText = "Blister beetles are plant-eating insects that can defoliate crops and release a toxic chemical harmful to livestock. They often appear in large groups and feed on leaves, flowers, and pods. To prevent them, handpick beetles early, use row covers, and maintain weed-free fields to reduce attraction."
            )
        )
        8 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent spider mites in your plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.spider,
                fullDialogText = "Spider mites are tiny, sap-sucking pests that cause yellowing and stippling on leaves, leading to plant damage and stunted growth. They thrive in hot, dry conditions and can reproduce rapidly, making them hard to control. To prevent them, regularly spray plants with water, introduce natural predators like predatory mites, and use insecticidal soap when necessary."
            )
        )
        9 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent ashy stem blight in your in plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.stem,
                fullDialogText = "Stem blight is a fungal disease that causes wilting, yellowing, and decaying of plant stems, leading to plant death if left untreated. It thrives in warm, humid environments and spreads through infected plant debris. To prevent it, practice crop rotation, remove infected plant material, and use fungicides as needed to protect healthy plants."
            )
        )
        10 -> listOf(
            PreventiveCardData(
                title = "Preventive Measure",
                description = "Prevent bacterial blight in your plants.",
                extraTips = "Remove Infected Leaves.",
                imageResId = R.drawable.bac,
                fullDialogText = "Bacterial blight is a plant disease that causes water-soaked lesions, wilting, and yellowing of leaves, often leading to premature plant death. It spreads through infected seeds, water, and tools, particularly in wet, humid conditions. To prevent it, use disease-free seeds, avoid overhead watering, and sanitize gardening tools regularly."
            )
        )

        // Add more weeks with corresponding cards here...
        else -> listOf(
            PreventiveCardData(
                title = "General Prevention",
                description = "Learn about pest prevention and control strategies.",
                extraTips = "Always inspect plants.\nMaintain soil health.",
                imageResId = R.drawable.img_7,
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

        cardDataList.forEach { cardData ->
            PreventiveCard(
                cardData = cardData,
                coroutineScope = coroutineScope,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
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
