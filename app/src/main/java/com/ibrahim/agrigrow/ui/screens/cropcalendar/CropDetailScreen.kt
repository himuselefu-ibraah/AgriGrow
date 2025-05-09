package com.ibrahim.agrigrow.ui.screens.cropcalendar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ibrahim.agrigrow.data.AppDatabase
import com.ibrahim.agrigrow.model.Crop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CropDetailScreen(cropId: Int?, navController: NavController) {
    var crop by remember { mutableStateOf<Crop?>(null) }
    val context = LocalContext.current

    LaunchedEffect(cropId) {
        crop = withContext(Dispatchers.IO) {
            cropId?.let {
                AppDatabase.getDatabase(context).cropDao().getCropById(it)
            }
        }
    }

    crop?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        fontSize = 26.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(label = "Region", value = it.region)
                    InfoRow(label = "Planting Month", value = it.plantingMonth)
                    InfoRow(label = "Harvesting Month", value = it.harvestingMonth)
                }
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Text(text = value, fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge)
    }
}

