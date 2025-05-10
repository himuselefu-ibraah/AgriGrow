package com.ibrahim.agrigrow.ui.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.ibrahim.agrigrow.R
import com.ibrahim.agrigrow.model.User
import com.ibrahim.agrigrow.navigation.ROUT_LOGIN
import com.ibrahim.agrigrow.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFF388E3C), Color(0xFF4CAF50))))
    ) {
        Image(
            painter = painterResource(id = R.drawable.anime7),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.2f)  // Slight transparency to allow the gradient background to show through
        )

        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }
        var role by remember { mutableStateOf("farmer") }
        val roleOptions = listOf("farmer", "admin")
        var expanded by remember { mutableStateOf(false) }

        val context = LocalContext.current
        val animatedAlpha by animateFloatAsState(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = LinearEasing),
            label = "Animated Alpha"
        )

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
                Text("Create Account", color = Color.White, fontSize = 40.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Username Input Field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color.White) },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon", tint = Color.White) },
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

            // Role Selection Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = role,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Role", color = Color.White) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.Green,
                        cursorColor = Color.White
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    roleOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                role = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Password Input Field with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = Color.White) },
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.img_14) else painterResource(R.drawable.img_13)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", tint = Color.White)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            // Confirm Password Input Field with Show/Hide Toggle
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", color = Color.White) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon", tint = Color.White) },
                trailingIcon = {
                    val image = if (confirmPasswordVisible) painterResource(R.drawable.img_14) else painterResource(R.drawable.img_13)
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password", tint = Color.White)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            Spacer(modifier = Modifier.height(50.dp))

            // Gradient Register Button
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
                        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                        } else if (password != confirmPassword) {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        } else {
                            authViewModel.registerUser(User(username = username, email = email, role = role, password = password))
                            onRegisterSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Register", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Login Navigation Button
            TextButton(onClick = { navController.navigate(ROUT_LOGIN) }) {
                Text("Already have an account? Login", color = Color.White)
            }
        }
    }
}
