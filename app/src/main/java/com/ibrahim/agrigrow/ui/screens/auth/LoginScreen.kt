package com.ibrahim.agrigrow.ui.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.navigation.ROUT_HOME
import com.ibrahim.agrigrow.navigation.ROUT_REGISTER
import com.ibrahim.agrigrow.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    // Greenish gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFF388E3C), Color(0xFF4CAF50)))))
    {
        Image(
            painter = painterResource(id = R.drawable.anime7),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.2f)  // Slight transparency to allow the gradient background to show through
        )

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val context = LocalContext.current

        // Observe login logic
        LaunchedEffect(authViewModel) {
            authViewModel.loggedInUser = { user ->
                if (user == null) {
                    Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                } else {
                    if (user.role == "admin") {
                        navController.navigate(ROUT_HOME) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    } else {
                        navController.navigate(ROUT_HOME) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                }
            }
        }

        // Main Column layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Welcome Text with animation
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                Text("Welcome Back", color = Color.White, fontSize = 40.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon", tint = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.Green,
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White) // Explicitly set text color
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Password Input with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = Color.White) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.Green,
                    cursorColor = Color.White
                ),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.img_14) else painterResource(R.drawable.img_13)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", tint = Color.White)
                    }
                },
                textStyle = TextStyle(color = Color.White) // Explicitly set text color
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Gradient Login Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                        } else {
                            authViewModel.loginUser(email, password)
                            navController.navigate("home")
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Register Navigation Button
            TextButton(onClick = { navController.navigate(ROUT_REGISTER) }) {
                Text("Don't have an account? Register", color = Color.White)
            }
        }
    }
}



