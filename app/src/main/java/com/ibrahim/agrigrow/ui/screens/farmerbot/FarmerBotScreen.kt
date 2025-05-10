// FarmerBotScreen.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerBotScreen(navController: NavController) {
    val topics = listOf(
        "Irrigation", "Land Preparation", "Crop Rotation", "Fertilizer Application",
        "Pest Control", "Disease Management", "Harvesting Techniques", "Soil Testing",
        "Post-Harvest Storage", "Organic Farming", "Composting", "Greenhouse Farming"
    )

    val explanations = mapOf(
        "Irrigation" to listOf(
            "Great choice! Let's talk about irrigation — a lifeline for crops in dry seasons.",
            "Irrigation ensures that plants receive water regularly, even when rainfall is scarce.",
            "There are several methods: drip, sprinkler, surface, and manual watering.",
            "Drip irrigation is water-efficient, delivering moisture directly to the root zone.",
            "Over-irrigation can harm plants and cause soil erosion, so balance is key.",
            "It's important to irrigate during cooler hours to reduce water evaporation.",
            "Choosing the right irrigation method improves yields and conserves water resources."
        ),
        "Land Preparation" to listOf(
            "Good pick! Land preparation is the farmer’s first step toward success.",
            "It involves clearing vegetation, plowing, harrowing, and leveling.",
            "This process creates a good environment for seed germination and root growth.",
            "Proper preparation improves water retention and helps eliminate pests and weeds.",
            "Tools used include hoes, tractors, and ox-drawn plows, depending on scale.",
            "Timely land prep ensures you plant at the start of the rainy season.",
            "It's the foundation of a productive season — strong beginnings matter!"
        ),
        "Crop Rotation" to listOf(
            "Smart move! Crop rotation keeps your soil happy and pests guessing.",
            "Rotating crops helps prevent disease buildup and soil nutrient depletion.",
            "It breaks pest cycles and enhances biodiversity on the farm.",
            "Legumes in rotation can fix nitrogen, boosting soil fertility naturally.",
            "This method reduces reliance on synthetic inputs and promotes sustainability.",
            "It also spreads risk — if one crop fails, others may still succeed.",
            "A well-planned crop rotation schedule leads to long-term productivity."
        ),
        "Fertilizer Application" to listOf(
            "Great! Fertilizer use is key to strong, healthy crops.",
            "Fertilizers supply essential nutrients like nitrogen, phosphorus, and potassium.",
            "Apply based on soil test results to avoid overuse or undernourishment.",
            "Timing matters — apply at planting, and again as crops grow.",
            "There are organic options like compost, and inorganic like NPK blends.",
            "Too much fertilizer can pollute water sources and damage plants.",
            "Balanced application improves yield, quality, and soil health."
        ),
        "Pest Control" to listOf(
            "Nice! Pest control protects your hard-earned crops from harm.",
            "Common pests include aphids, caterpillars, and weevils.",
            "Integrated Pest Management (IPM) combines biological, physical, and chemical methods.",
            "Natural predators like ladybugs help control pests without chemicals.",
            "Proper sanitation and crop rotation reduce pest infestations.",
            "Use approved pesticides carefully, following instructions.",
            "Monitoring your field regularly ensures early detection and timely response."
        ),
        "Disease Management" to listOf(
            "Wise choice! Crop diseases can ruin an entire season if unmanaged.",
            "Diseases may be fungal, bacterial, or viral — each requires specific control.",
            "Early symptoms include leaf discoloration, wilting, or stunted growth.",
            "Use certified seeds and resistant crop varieties for prevention.",
            "Apply fungicides and practice field hygiene to limit spread.",
            "Crop rotation also helps by interrupting disease cycles.",
            "Quick identification and action are critical to minimizing losses."
        ),
        "Harvesting Techniques" to listOf(
            "Excellent! Harvesting at the right time preserves crop quality.",
            "Mature crops should be harvested before they over-ripen or rot.",
            "Different crops require unique tools — from sickles to combine harvesters.",
            "Handle produce gently to avoid bruising and spoilage.",
            "Harvest early in the morning when it's cooler for better freshness.",
            "Use clean, dry containers for collection to prevent contamination.",
            "Post-harvest care is equally vital to reduce losses and improve market value."
        ),
        "Soil Testing" to listOf(
            "Great start! Knowing your soil is knowing your farm’s future.",
            "Soil testing reveals nutrient levels and pH balance.",
            "Samples should be taken before planting and sent to a lab.",
            "The results guide fertilizer and lime application accurately.",
            "Healthy soil equals healthy plants — never guess your inputs.",
            "Testing saves money by preventing over-application.",
            "It also supports environmental protection by reducing runoff."
        ),
        "Post-Harvest Storage" to listOf(
            "Very important! Post-harvest losses hurt both income and food security.",
            "Store grains in clean, dry, and airtight containers.",
            "Use structures like granaries or silos with moisture control.",
            "Pests and fungi are common threats — inspect regularly.",
            "Avoid stacking wet produce to prevent rotting.",
            "Label and organize stock to monitor age and rotation.",
            "Good storage prolongs shelf-life and maintains quality for the market."
        ),
        "Organic Farming" to listOf(
            "Smart pick! Organic farming promotes health and sustainability.",
            "It avoids synthetic fertilizers, pesticides, and GMOs.",
            "Focus is on compost, green manure, and crop diversity.",
            "Soil fertility is maintained using natural techniques.",
            "Organic produce often fetches higher prices in markets.",
            "Certification may be needed to label your produce as organic.",
            "Long-term, this method enhances biodiversity and farmer wellbeing."
        ),
        "Composting" to listOf(
            "Excellent choice! Composting turns waste into gold for your farm.",
            "Use kitchen waste, plant matter, and manure to create compost.",
            "Compost enriches soil with organic matter and nutrients.",
            "Maintain moisture and aeration for effective decomposition.",
            "It reduces reliance on chemical fertilizers.",
            "Composting is eco-friendly and cost-effective.",
            "Your crops will thank you with healthier growth and better yield."
        ),
        "Greenhouse Farming" to listOf(
            "Great innovation! Greenhouse farming controls the growing environment.",
            "It protects crops from harsh weather and pests.",
            "Crops like tomatoes, peppers, and herbs thrive in greenhouses.",
            "You can farm year-round and maximize yields.",
            "It requires investment but offers high returns.",
            "Ventilation, temperature, and humidity must be managed well.",
            "Greenhouses enable precision farming with great efficiency."
        )
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedTopic by remember { mutableStateOf("") }
    var visibleLines by remember { mutableStateOf(0) }

    LaunchedEffect(selectedTopic) {
        visibleLines = 0
        explanations[selectedTopic]?.let {
            for (i in 1..it.size) {
                visibleLines = i
                delay(1500L)
            }
        }
    }

    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Ask Mkulima \uD83D\uDE02", fontWeight = FontWeight.Bold)
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
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What should I help you with today?",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedTopic,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Choose a Topic") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    topics.forEach { topic ->
                        DropdownMenuItem(
                            text = { Text(topic) },
                            onClick = {
                                selectedTopic = topic
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(
                visible = selectedTopic.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = selectedTopic,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        explanations[selectedTopic]?.take(visibleLines)?.forEach {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}





