package com.ibrahim.agrigrow.ui.screens.pest

import android.content.Intent
import android.net.Uri
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R

data class PestInfo(
    val title: String,
    val subtitle: String,
    val imageResId: Int,
    val videoUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PestScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val sectionedPests = listOf(
        listOf(
            PestInfo("Virus", "Apple Mosaic", R.drawable.pest1, "https://www.youtube.com/watch?v=video1"),
            PestInfo("Fungus", "Stecklenberger disease", R.drawable.pest2, "https://www.youtube.com/watch?v=video2"),
            PestInfo("Insect", "Aphids", R.drawable.pest3, "https://www.youtube.com/watch?v=video2")
        ),
        listOf(
            PestInfo("Fungus", "Peach Leaf Curl", R.drawable.leafcurl, "https://www.youtube.com/watch?v=video3"),
            PestInfo("Fungus", "Cherry Leaf Spot", R.drawable.cherry, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Plum Rust", R.drawable.pest6, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Dead Arm", R.drawable.pest5, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Blossom Blight", R.drawable.pest4, "https://www.youtube.com/watch?v=video4")
        ),
        listOf(
            PestInfo("Fungus", "Brown Rot", R.drawable.brown, "https://www.youtube.com/watch?v=video5"),
            PestInfo("Fungus", "Peach Leaf Curl", R.drawable.leafcurl, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Cherry Leaf Spot", R.drawable.cherry, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Plum Rust", R.drawable.pest6, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Peach Scab", R.drawable.peach, "https://www.youtube.com/watch?v=video6")
        ),
        listOf(
            PestInfo("Fungus", "Brown Rot", R.drawable.brown, "https://www.youtube.com/watch?v=video5"),
            PestInfo("Fungus", "Blossom Blight and Fruit Rot", R.drawable.blosom, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Plum Rust", R.drawable.pest6, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Peach Scab", R.drawable.peach, "https://www.youtube.com/watch?v=video6"),
            PestInfo("Fungus", "Botrytis Blight", R.drawable.bot, "https://www.youtube.com/watch?v=video7"),
            PestInfo("Fungus", "Cherry Leaf Spot", R.drawable.cherry, "https://www.youtube.com/watch?v=video8")
        ),
        listOf(
            PestInfo("Fungus", "Brown Rot", R.drawable.brown, "https://www.youtube.com/watch?v=video5"),
            PestInfo("Fungus", "Blossom Blight and Fruit Rot", R.drawable.blosom, "https://www.youtube.com/watch?v=video4"),
            PestInfo("Fungus", "Peach Leaf Curl", R.drawable.leafcurl, "https://www.youtube.com/watch?v=video3"),
            PestInfo("Fungus", "Botrytis Blight", R.drawable.bot, "https://www.youtube.com/watch?v=video7"),
            PestInfo("Fungus", "Anthracnose of Almond", R.drawable.anthracnose, "https://www.youtube.com/watch?v=video7")
        )
    )

    val sectionTitles = listOf(
        "Seedling Stage",
        "Vegetative Stage",
        "Flowering Stage",
        "Fruiting Stage",
        "Harvesting Stage "
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pest and Diseases", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    navController.navigate("pest")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding(),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Learn More on Pests", color = Color.White, fontSize = 18.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .background(Color(0xFFF4F4F4))
        ) {
            Text(
                text = "Protect your crops from pests and viruses. Learn how to identify and manage plant diseases with expert videos.",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            sectionedPests.forEachIndexed { index, pests ->
                SectionTitleWithCards(
                    sectionTitle = sectionTitles.getOrNull(index) ?: "Pest Section ${index + 1}",
                    pests = pests,
                    onCardClick = { videoUrl ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                        context.startActivity(intent)
                    }
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
fun SectionTitleWithCards(sectionTitle: String, pests: List<PestInfo>, onCardClick: (String) -> Unit) {
    Text(
        text = sectionTitle,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        pests.forEach { pest ->
            VirusCard(pestInfo = pest, onClick = { onCardClick(pest.videoUrl) })
        }
    }
}

@Composable
fun VirusCard(pestInfo: PestInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(220.dp)
            .clickable { onClick() }
            .shadow(6.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = pestInfo.imageResId),
                contentDescription = pestInfo.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(pestInfo.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                Text(pestInfo.subtitle, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = Color(0xFF4CAF50))
            }
        }
    }
}







