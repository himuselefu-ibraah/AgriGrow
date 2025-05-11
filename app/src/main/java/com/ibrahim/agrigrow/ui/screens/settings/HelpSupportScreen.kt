package com.ibrahim.agrigrow.ui.screens.settings

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSupportScreen(navController: NavController) {
    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Help and Support", fontWeight = FontWeight.Bold)
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
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Need Help?",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = """
                        We're here to help you make the most out of the AgriGrow app. Whether you need assistance with features, troubleshooting issues, or giving feedback, this page is your one-stop guide.

                        🔧 Common Issues:
                        - Not receiving notifications? Ensure permissions and settings are enabled.
                        - App crashing? Try clearing cache or updating the app.
                        - Feature not working? Double-check internet connection and app version.

                        💡 How to Use:
                        - Tap on Crop Calendar to view planting seasons.
                        - Use the Fertilizer Guide to choose the best fertilizer for your crops.
                        - Navigate to Livestock Health to track animal well-being.
                        - Use Ask Mkulima to explore on different Agricultural Topics.

                        ✉️ Contact Support:
                        - Email: support@smartfarmassistant.com
                        - Phone: +254 721 715 645
                        - WhatsApp: +254 721 715 645

                        📢 Feedback:
                        We value your feedback to improve the app. Go to Settings > Feedback to share your thoughts or report a bug.

                        🕐 Support Hours:
                        Monday – Friday: 8:00 AM – 6:00 PM
                        Saturday: 9:00 AM – 1:00 PM

                        🧠 FAQs:
                        1. Can I use the app offline?
                           Yes, most features like the Crop Calendar and Livestock Records work offline after initial data sync.
                        
                        2. How do I backup my data?
                           Go to Settings > Backup, and follow the steps to export data locally or to cloud.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),

                    ) {
                    Text("Back")
                }
            }
        }
    )
}
