package com.ibrahim.agrigrow.ui.screens.settings

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
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
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Private Policy", fontWeight = FontWeight.Bold)
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
                    .fillMaxSize() // Make sure it takes up the full screen
                    .padding(padding)
                    .verticalScroll(rememberScrollState()) // Enable scrolling
                    .padding(16.dp) // Padding for better readability
            ) {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = """
                        At AgriGrow, we value your privacy and are committed to protecting your personal data. This Privacy Policy explains how we collect, use, and protect your information when you use our app. By using the app, you agree to the collection and use of information in accordance with this policy.

                        1. Information Collection and Use  
                        We collect the following types of information to provide and improve our services:
                        - Personal Information: Information such as name, email, and phone number may be collected when you create a profile.
                        - Device Information: We collect data about your device such as model, operating system, and app usage for improving performance.
                        - Location Data: If you enable location features (e.g., weather or crop calendar), we may collect and process location data.
                        - Health Data: We may collect health-related data of livestock or crops to give farming recommendations, but this data is stored securely.

                        2. Use of Information  
                        The information we collect is used for the following purposes:
                        - To personalize your experience and provide farming tips, weather forecasts, and other services.
                        - To send notifications related to farming tasks, reminders, and updates.
                        - To enhance the functionality of the app and fix any technical issues.

                        3. Data Security  
                        We take reasonable measures to protect your personal data, including encryption and secure storage. However, no method of transmission over the internet is completely secure, and we cannot guarantee the security of your information.

                        4. Third-Party Services  
                        We may use third-party services (like weather APIs and Pest & disease detection tool) that may collect information to provide services. These third parties have their own privacy policies, and we encourage you to review them.

                        5. Data Retention  
                        We retain your information only for as long as necessary to fulfill the purposes outlined in this Privacy Policy. After this period, your data will be securely deleted.

                        6. Your Rights  
                        You have the right to:
                        - Access your personal data.
                        - Correct or update your information.
                        - Request the deletion of your personal information.
                        - Opt-out of notifications and marketing communications.

                        7. Children’s Privacy  
                        Our app does not knowingly collect personal data from children under the age of 13. If we discover that we have inadvertently collected personal information from children, we will take steps to delete such information.

                        8. Changes to This Privacy Policy  
                        We may update our Privacy Policy occasionally. Any changes will be posted within the app, and the date of the latest update will be indicated.

                        9. Contact Us  
                        If you have any questions or concerns about this Privacy Policy or your personal data, please contact us at [ibrahimchristopher004@gmail.com].
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),

                    ) {
                    Text(text = "Back")
                }
            }
        }
    )
}
