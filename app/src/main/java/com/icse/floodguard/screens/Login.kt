package com.icse.floodguard.screens

import android.widget.Toast
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.icse.floodguard.ui.theme.lightCyan

@Composable
fun Login(navController: NavController){

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()


    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }
    var loading by remember{
        mutableStateOf(false)
    }

    var passwordVisible by remember{
        mutableStateOf(false)
    }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alphaAnim by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                listOf(
                    Color(0xFF141E30),
                    Color(0xFF243B55)
                )

            )
        )
    ){
        Box(
            modifier = Modifier.size(250.dp)
                .offset((-60).dp, (-60).dp)
                .alpha(alphaAnim)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f))

        )

        Box(
            modifier = Modifier.size(180.dp)
                .align(Alignment.BottomEnd)
                .offset(40.dp, 40.dp)
                .alpha(alphaAnim)
                .clip(CircleShape)
                .background(Color.Cyan.copy(alpha = 0.2f))
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Welcome Back!",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))

            Card(
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.12f)
                )
            ){
                Column(modifier = Modifier.padding(24.dp)){
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Email", color = Color.White)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        leadingIcon = {
                            Icon(Icons.Default.Person, null)
                        },
                        shape = RoundedCornerShape(18.dp)

                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Password", color = Color.White)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        leadingIcon = {
                            Icon(Icons.Default.Lock, null)
                        },
                        shape = RoundedCornerShape(18.dp),
                        visualTransformation = if(passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),

                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            if(email.isEmpty()||
                                password.isEmpty()
                            ) {
                                Toast.makeText(context,"Please fill all fields", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            loading = true
                            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                                loading = false
                                if(task.isSuccessful){
                                    Toast.makeText(context,"Login Successfull", Toast.LENGTH_SHORT).show()
                                    navController.navigate("home")
                                }
                                else{
                                    Toast.makeText(context,"Login Failed", Toast.LENGTH_LONG).show()

                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightCyan
                        )


                    )
                    {
                        if(loading){
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        else{
                            Text(text = "LOGIN", fontSize = 18.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Text(text = "Don't have an account?",
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Sign Up",
                            color = Color.Cyan,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable{
                                navController.navigate("signup")
                            }

                        )
                    }
                }
            }
        }

    }

}