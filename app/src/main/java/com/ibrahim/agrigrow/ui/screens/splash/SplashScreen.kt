package com.ibrahim.agrigrow.ui.screens.splash


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.navigation.ROUT_LOGIN
import com.ibrahim.agrigrow.navigation.ROUT_WELCOME
import com.ibrahim.agrigrow.ui.theme.newgreen


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){

    //Navigation

    val coroutine = rememberCoroutineScope()
    coroutine.launch {

        delay(2000)
        navController.navigate(ROUT_WELCOME)

    }

    //End of Navigation

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(newgreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){




        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "AgriGrow",
            fontSize = 45.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White

        )












    }


}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())

}
