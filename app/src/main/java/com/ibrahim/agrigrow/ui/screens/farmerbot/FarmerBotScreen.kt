// FarmerBotScreen.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

    val followUpContent = mapOf(
        "Irrigation" to listOf(
            "1. Drip Irrigation – delivers water directly to plant roots with minimal waste.",
            "2. Sprinkler Irrigation – mimics rainfall by spraying water overhead.",
            "3. Surface Irrigation – water flows over soil by gravity.",
            "4. Manual Irrigation – involves using cans or buckets, mostly on small farms."
        ),
        "Soil Testing" to listOf(
            " \uD83C\uDF31 1. Physical Methods\n" +
                    "These examine the physical properties of soil:\n" +
                    "\n" +
                    "Soil Texture Test (Feel Method): Rubbing moist soil between fingers to determine if it's sandy, loamy, or clayey.\n" +
                    "\n" +
                    "Structure Test: Examining how soil clumps together or breaks apart.\n" +
                    "\n" +
                    "Water Infiltration Test: Pouring water into a hole and timing how long it takes to soak in.\n" +
                    "\n" +
                    "Soil Color Test: Using a Munsell soil color chart to identify soil types and organic matter.",
            " \uD83E\uDDEA 2. Chemical Methods\n" +
                    "These test for nutrient levels and pH:\n" +
                    "\n" +
                    "pH Testing (Acidity/Alkalinity):\n" +
                    "\n" +
                    "Using pH test strips or digital pH meters.\n" +
                    "\n" +
                    "Kits available for farmers or lab-based analysis.\n" +
                    "\n" +
                    "Nutrient Testing (NPK and Others):\n" +
                    "\n" +
                    "Nitrogen (N), Phosphorus (P), Potassium (K) test kits.\n" +
                    "\n" +
                    "Micronutrients (e.g., calcium, magnesium, sulfur) tested in labs.\n" +
                    "\n" +
                    "Salinity Test: Measures salt levels, usually in arid areas.\n" +
                    "\n" +
                    "CEC (Cation Exchange Capacity): Determines soil's ability to hold nutrients.",
            " \uD83E\uDDA0 3. Biological Methods\n" +
                    "These assess the soil's living components:\n" +
                    "\n" +
                    "Microbial Activity Test: Measures respiration of microbes to assess soil health.\n" +
                    "\n" +
                    "Earthworm Count: Presence of earthworms indicates good organic matter and soil fertility.\n" +
                    "\n" +
                    "Soil Respiration Test: Detects CO₂ levels from microbial activity",
            "\uD83E\uDDF0 4. Laboratory Testing\n" +
                    "Done by professionals for accurate, detailed results:\n" +
                    "\n" +
                    "Comprehensive Soil Analysis: Covers pH, texture, nutrient levels, organic matter, etc.\n" +
                    "\n" +
                    "Tissue Testing (for plants): Assesses if soil is supplying nutrients to plants effectively.",
            "\uD83D\uDCF1 5. Modern and Digital Methods\n" +
                    "Technology-enhanced methods include:\n" +
                    "\n" +
                    "Portable Soil Test Kits & Devices: Handheld digital tools used in the field.\n" +
                    "\n" +
                    "Mobile Apps with Soil Sensors: Use smart sensors connected to smartphones.\n" +
                    "\n" +
                    "Remote Sensing & GIS: Use satellite data to analyze soil health over wide areas."
        ),
        "Land Preparation" to listOf(
            "1. Tilling: Involves using a plow or tiller to break up soil, improve aeration, and mix organic matter to prepare the land for planting.",
            "2. Clearing: Removing weeds, rocks, tree stumps, and debris from the land to create a clean and level surface for planting..",
            "3. Harrowing: Using a harrow to break up clods of soil, level the surface, and create a fine seedbed for sowing.",
            "4.Harrowing: Using a harrow to break up clods of soil, level the surface, and create a fine seedbed for sowing.",
            "5.Mounding or Ridging: Creating raised beds or ridges to improve drainage, especially in poorly-drained areas.",
            "6.Mulching: Applying a layer of organic material like straw or leaves to the soil surface to retain moisture, suppress weeds, and enhance soil health.",
            "7.Soil Testing and Amending: Testing soil for pH, nutrient levels, and texture, followed by adding necessary amendments like compost, lime, or fertilizers to improve soil fertility."

        ),
        "Crop Rotation" to listOf(
            "1.Simple Crop Rotation: Involves rotating two or more crops in a specific sequence each season, such as alternating between legumes and non-legumes to restore soil nitrogen levels..",
            "2.Three-Crop Rotation: Involves rotating three different types of crops, like a root crop (e.g., carrots), a leafy crop (e.g., spinach), and a fruiting crop (e.g., tomatoes). This method helps break pest and disease cycles while improving soil health..",
            "3.Legume and Non-Legume Rotation: Alternating between legumes (which fix nitrogen) and non-leguminous crops (like cereals) to improve soil fertility and reduce the need for chemical fertilizers..",
            "4.Seasonal Rotation: This method focuses on rotating crops based on the growing season, planting crops that thrive in different seasons (e.g., planting cool-season crops like cabbage in winter and warm-season crops like corn in summer)..",
            "5.Mixed Rotation: Growing different crops on the same field in alternating seasons, which helps to improve biodiversity, soil structure, and pest control..",
            "6.Cover Crop Rotation: Planting cover crops like clover or rye during the off-season to improve soil health, prevent erosion, and add organic matter.."
        ),
        "Fertilizer Application" to listOf(
            "1.Broadcasting \uD83C\uDF3E: Spreading fertilizer evenly across the soil surface, either by hand or with a spreader.",
            "2.Side-Dressing \uD83E\uDDD1\u200D\uD83C\uDF3E: Applying fertilizer along the sides of growing plants, typically during the growing season to boost nutrient uptake.",
            "3.Fertigation \uD83D\uDCA7: Mixing fertilizer with irrigation water and applying it directly to the root zone.",
            "4.Foliar Feeding \uD83C\uDF43: Spraying liquid fertilizer directly onto plant leaves for quick absorption of nutrients.",
            "5.Hole or Band Application \uD83D\uDD73\uFE0F: Placing fertilizer in holes or bands around the base of the plant to ensure nutrients are close to the roots.",
            "6.Top Dressing \uD83C\uDFE1: Spreading fertilizer on top of the soil after planting to support plant growth during the growing season.",

        ),
        "Pest Control" to listOf(
            "1.Chemical Control \uD83E\uDDEA – Use of pesticides to kill or repel pests.",
            "2.Biological Control \uD83D\uDC1D – Introduce natural enemies like parasitoids or predators.",
            "3.Cultural Control \uD83D\uDE9C – Rotate crops, remove infected plants, and maintain field hygiene..",
            "4.Mechanical Control \uD83E\uDEA4 – Use traps, hand-picking, or barriers like nets.",
            "6.Organic Methods \uD83C\uDF3F – Apply natural sprays like neem oil or garlic solutions.",
            "7.IPM (Integrated Pest Management) \uD83E\uDDE0 – Smartly combine all methods for sustainable control.",
        ),
        "Disease Management" to listOf(
            "1.Crop Rotation \uD83D\uDD04 – Avoid planting the same crop repeatedly to break disease cycles. ",
            "2.Resistant Varieties \uD83C\uDF3E – Grow disease-resistant crop types.",
            "3.Sanitation \uD83E\uDDF9 – Remove and destroy infected plant parts to reduce disease spread.",
            "4.Fungicide Use \uD83E\uDDF4 – Apply approved fungicides where necessary to control fungal infections.",
            "5.Soil Health \uD83E\uDEB1 – Maintain fertile, well-drained soil to support healthy, disease-resistant plants.",
            "6.Monitoring and Early Detection \uD83D\uDC40 – Regularly inspect crops to catch and manage diseases early.",

        ),
        "Harvesting Techniques" to listOf(
            "1.Manual Harvesting ✂\uFE0F – Using hands or simple tools like sickles or knives to pick crops, ideal for delicate produce like fruits or vegetables. ",
            "2.Mechanical Harvesting \uD83D\uDE9C – Using machines like combine harvesters or threshers for large-scale crops like maize, wheat, or rice.",
            "3.Selective Harvesting \uD83E\uDDFA – Picking only mature produce at intervals, common in crops like tomatoes or tea.",
            "4.Strip Harvesting \uD83C\uDF3F – Removing all produce from a plant or area at once, usually used for grains or beans.",
            "5.Shaking or Beating \uD83C\uDF30 – Shaking trees or using sticks to harvest nuts or fruits like mangoes.",

        ),
        "Post-Harvest Storage" to listOf(
            "1.Drying \uD83C\uDF1E – Reduce moisture content in grains, legumes, or seeds to prevent mold and rot. ",
            "2.Cool Storage ❄\uFE0F – Use refrigerators or cool rooms for perishable produce like fruits and vegetables.",
            "2.Airtight Containers \uD83E\uDEA3 – Store dried produce in sealed containers to block pests and moisture.",
            "2.Granaries & Silos \uD83C\uDFDA\uFE0F – Use traditional or modern grain storage structures to protect bulk harvests.",
            "2.Use of Preservatives \uD83E\uDDF4 – Apply safe chemicals or natural treatments to extend shelf life.",
            "2.Regular Inspection \uD83D\uDC40 – Monitor stored crops regularly for signs of spoilage or pests.",

        ),
        "Organic Farming" to listOf(
            "1.No synthetic fertilizers or pesticides \uD83D\uDEAB – Only natural compost, manure, and biological pest control are used. ",
            "2.Crop rotation and diversification \uD83D\uDD04 – Improves soil fertility and controls pests naturally.",
            "3.Soil health focus \uD83E\uDEB1 – Use of compost, green manure, and cover crops to maintain healthy, living soil.",
            "4.Biodiversity \uD83D\uDC1E – Encourages natural predators and maintains ecological balance.",
            "5.Animal welfare \uD83D\uDC04 – Livestock are raised with access to the outdoors, natural feed, and no antibiotics or hormones.",

        ),
        "Composting" to listOf(
            "1.Pit Composting \uD83D\uDD73\uFE0F – Organic materials are dumped in a pit, covered with soil, and left to decompose. ",
            "2.Heap Composting \uD83E\uDEB5 – Waste is piled above ground in layers and turned regularly for aeration..",
            "3.Vermicomposting \uD83E\uDEB1 – Uses worms (like red wigglers) to break down organic material faster.",
            "4.Trench Composting \uD83C\uDF31 – Organic matter is buried in a trench between crop rows to enrich the soil directly.",
            "5.Bin Composting \uD83E\uDDFA – Compost is made in containers or bins, making it more organized and manageable..",

        ),
        "Greenhouse Farming" to listOf(
            "\uD83C\uDF3F 1. Soil-Based Greenhouse Farming\n" +
                    "Crops are grown in natural soil inside the greenhouse.\n" +
                    "\n" +
                    "The soil is often enriched with organic matter or fertilizers.\n" +
                    "\n" +
                    "Requires regular irrigation and pest control.\n" +
                    "\n" +
                    "✅ Common in low-tech greenhouses in Kenya and other developing regions. ",
            "\uD83D\uDCA7 2. Hydroponic Greenhouse Farming\n" +
                    "Crops grow in a nutrient-rich water solution without soil.\n" +
                    "\n" +
                    "Plants are supported using materials like coco peat, rock wool, or clay pellets.\n" +
                    "\n" +
                    "Nutrients are directly fed to the roots through water.\n" +
                    "\n" +
                    "✅ Efficient use of water and nutrients; suitable for vegetables like lettuce and tomatoes.",
            "\uD83E\uDEB4 3. Aquaponic Greenhouse Farming\n" +
                    "A combination of hydroponics and aquaculture (raising fish).\n" +
                    "\n" +
                    "Fish waste provides nutrients for the plants, and the plants clean the water for the fish.\n" +
                    "\n" +
                    "A closed-loop, sustainable system.\n" +
                    "\n" +
                    "✅ Eco-friendly; produces both fish and vegetables.",
            "\uD83E\uDDEA 4. Aeroponic Greenhouse Farming\n" +
                    "Plants are grown in the air with roots hanging freely.\n" +
                    "\n" +
                    "Nutrients are delivered as a mist to the roots.\n" +
                    "\n" +
                    "No soil or growing medium is used.\n" +
                    "\n" +
                    "✅ Uses less water than hydroponics; ideal for high-tech systems.",
            "\uD83D\uDD06 5. Climate-Controlled (Hi-Tech) Greenhouse Farming\n" +
                    "Equipped with automated systems for temperature, humidity, CO₂, and light control.\n" +
                    "\n" +
                    "Often uses hydroponics or aeroponics.\n" +
                    "\n" +
                    "Sensors and computers manage plant growth conditions.\n" +
                    "\n" +
                    "✅ Ideal for year-round production and sensitive crops.",
            "\uD83C\uDFD7\uFE0F 6. Tunnel or Low-Tech Greenhouse Farming\n" +
                    "Simple structures made of polythene or shade nets.\n" +
                    "\n" +
                    "Relies on natural sunlight and ventilation.\n" +
                    "\n" +
                    "Used for basic crop protection and season extension.\n" +
                    "\n" +
                    "✅ Affordable and widely used in Kenya for vegetables and herbs.",
            "\uD83C\uDF31 7. Vertical Greenhouse Farming\n" +
                    "Plants are grown in stacked layers inside the greenhouse.\n" +
                    "\n" +
                    "Often combined with hydroponics or aeroponics.\n" +
                    "\n" +
                    "Maximizes space and increases production.\n" +
                    "\n" +
                    "✅ Good for urban or space-limited areas.",
        ),






        // Add other topics' follow-up content here
    )

    val noResponses = mapOf(
        "Irrigation" to "Alright! Have a nice day and feel free to ask anything else about irrigation 😊",
        "Land Preparation" to "No problem! Wishing you a productive farming day. Come back anytime!",
        "Crop Rotation" to "Sure! Crop rotation can wait. Ask me anything else when you're ready.",
        "Fertilizer Application" to "Okay! Have a fertile day ahead, and ask about fertilizers anytime.",
        "Pest Control" to "Understood! Stay pest-free out there. Come back if you need more tips.",
        "Disease Management" to "Alright! Stay vigilant with your crops and reach out anytime.",
        "Harvesting Techniques" to "No worries! May your harvests be bountiful. Ask again soon!",
        "Soil Testing" to "Okay! Test the soil when ready. I'm here to help whenever needed.",
        "Post-Harvest Storage" to "Sure thing! Store well, sell well. Happy farming!",
        "Organic Farming" to "No problem! Stay green and ask more when you wish.",
        "Composting" to "Got it! Composting can wait. Come back when you're ready!",
        "Greenhouse Farming" to "Alright! Greenhouse tips are just a click away when you need them!"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedTopic by remember { mutableStateOf("") }
    var visibleLines by remember { mutableStateOf(0) }
    var showFollowUp by remember { mutableStateOf(false) }
    var showFollowUpLines by remember { mutableStateOf(0) }
    var followUpQuestionAsked by remember { mutableStateOf(false) }
    var noResponseMessage by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(selectedTopic) {
        visibleLines = 0
        followUpQuestionAsked = false
        showFollowUp = false
        showFollowUpLines = 0
        noResponseMessage = ""
        explanations[selectedTopic]?.let {
            for (i in 1..it.size) {
                visibleLines = i
                delay(1500L)
            }
            followUpQuestionAsked = true
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ask Mkulima 😅", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("What should I help you with today?", fontSize = 18.sp, textAlign = TextAlign.Center)
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
                    modifier = Modifier.menuAnchor().fillMaxWidth()
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
                        Text(selectedTopic, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(8.dp))

                        explanations[selectedTopic]?.take(visibleLines)?.forEach {
                            Text(text = it, fontSize = 16.sp, modifier = Modifier.padding(bottom = 12.dp))
                        }

                        if (followUpQuestionAsked && !showFollowUp && followUpContent.containsKey(selectedTopic)) {
                            Text(
                                text = "Would you like to see different methods of ${selectedTopic.lowercase()}?",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                            Row {
                                Button(
                                    onClick = {
                                        showFollowUp = true
                                        showFollowUpLines = 0
                                    },
                                    modifier = Modifier.padding(8.dp)
                                ) { Text("Yes") }

                                OutlinedButton(
                                    onClick = { followUpQuestionAsked = false },
                                    modifier = Modifier.padding(8.dp)
                                ) { Text("No") }
                            }
                        }

                        if (showFollowUp) {
                            LaunchedEffect(selectedTopic + showFollowUp) {
                                followUpContent[selectedTopic]?.let {
                                    for (i in 1..it.size) {
                                        showFollowUpLines = i
                                        delay(1500L)
                                    }
                                }
                            }

                            followUpContent[selectedTopic]?.take(showFollowUpLines)?.forEach {
                                Text(text = it, fontSize = 15.sp, modifier = Modifier.padding(bottom = 10.dp))
                            }
                        }
                        if (noResponseMessage.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = noResponseMessage,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                    }
                }
            }
        }
    }
}
}
