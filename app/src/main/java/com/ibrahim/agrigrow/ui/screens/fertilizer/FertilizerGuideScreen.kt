package com.ibrahim.agrigrow.ui.screens.fertilizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahim.agrigrow.R
import kotlinx.coroutines.delay

data class Fertilizer(
    val name: String,
    val description: String,
    val imageResId: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FertilizerGuideScreen(onBackClick = { finish() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FertilizerGuideScreen(onBackClick: () -> Unit) {
    val fertilizers = listOf(
        Fertilizer("DAP", "Promotes strong root growth at planting stage.", R.drawable.img_24),
        Fertilizer("CAN", "Used for top dressing to supply nitrogen.", R.drawable.img_24),
        Fertilizer("Urea", "High nitrogen content for fast plant growth.", R.drawable.img_24),
        Fertilizer("NPK", "Balanced fertilizer for all crop stages.", R.drawable.img_24),
        Fertilizer("MAP", "Rich in phosphorus, promotes early growth.", R.drawable.img_24),
        Fertilizer("SSP", "A good source of phosphorus for flowering.", R.drawable.img_24),
        Fertilizer("MOP", "Supplies potassium for fruit and root crops.", R.drawable.img_24),
        Fertilizer("Manure", "Improves soil organic matter and fertility.", R.drawable.img_24),
        Fertilizer("Compost", "Enhances soil structure and nutrients.", R.drawable.img_24),
        Fertilizer("Bone Meal", "Provides phosphorus and calcium.", R.drawable.img_24),
        Fertilizer("Vermicompost", "Nutrient-rich compost from worms.", R.drawable.img_24),
        Fertilizer("Poultry Manure", "Organic nitrogen-rich fertilizer.", R.drawable.img_24),
        Fertilizer("Fish Emulsion", "Organic liquid fertilizer for vegetables.", R.drawable.img_24),
        Fertilizer("Seaweed Extract", "Boosts plant growth and stress resistance.", R.drawable.img_24),
        Fertilizer("Green Manure", "Crops that improve soil fertility.", R.drawable.img_24),
        Fertilizer("Biochar", "Carbon-rich soil enhancer.", R.drawable.img_24),
        Fertilizer("Molasses", "Feeds beneficial soil microbes.", R.drawable.img_24),
        Fertilizer("Gypsum", "Improves soil texture and reduces salinity.", R.drawable.img_24),
        Fertilizer("Lime", "Reduces soil acidity (pH).", R.drawable.img_24),
        Fertilizer("Neem Cake", "Organic fertilizer with pest control properties.", R.drawable.img_24),
        Fertilizer("Rock Phosphate", "Slow-release phosphorus.", R.drawable.img_24),
        Fertilizer("Zinc Sulfate", "Micronutrient for plant enzyme function.", R.drawable.img_24),
        Fertilizer("Borax", "Corrects boron deficiency in plants.", R.drawable.img_24),
        Fertilizer("Iron Chelate", "Fixes yellowing due to iron deficiency.", R.drawable.img_24),
        Fertilizer("Calcium Nitrate", "Prevents blossom end rot in tomatoes.", R.drawable.img_24),
        Fertilizer("Potassium Nitrate", "Combines potassium and nitrogen.", R.drawable.img_24),
        Fertilizer("Humic Acid", "Improves nutrient absorption.", R.drawable.img_24),
        Fertilizer("Coffee Husk", "Organic waste used as compost.", R.drawable.img_24),
        Fertilizer("Wood Ash", "Source of potassium and lime.", R.drawable.img_24),
        Fertilizer("Farmyard Manure", "Well-rotted dung and urine of farm animals.", R.drawable.img_24),
        Fertilizer("Coco Peat", "Retains moisture and improves soil aeration.", R.drawable.img_24),
        Fertilizer("Guano", "Natural fertilizer from seabird/bat droppings.", R.drawable.img_24),
        Fertilizer("Slurry", "Liquid by-product of biogas with nutrients.", R.drawable.img_24),
        Fertilizer("Silicate Slag", "Source of silicon and calcium.", R.drawable.img_24),
        Fertilizer("Spent Mushroom Substrate", "Organic residue from mushroom farming.", R.drawable.img_24),
        Fertilizer("Azolla", "Nitrogen-rich aquatic fern used as green manure.", R.drawable.img_24)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Fertilizer Guide", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF4CAF50)) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* TODO: Navigate to Home */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
                    label = { Text("Home", color = Color.White) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Navigate to Settings */ },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) },
                    label = { Text("Settings", color = Color.White) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Navigate to Profile */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Profile", color = Color.White) }
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF4F4F4)),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(fertilizers) { index, fertilizer ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    delay(index * 100L)
                    visible = true
                }
                AnimatedVisibility(visible = visible, enter = fadeIn()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = fertilizer.imageResId),
                                contentDescription = fertilizer.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0x88000000))
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Text(
                                    text = fertilizer.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = fertilizer.description,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}




