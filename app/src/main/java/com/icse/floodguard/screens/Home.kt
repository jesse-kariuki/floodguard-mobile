package com.icse.floodguard.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.icse.floodguard.ui.theme.DarkBlue
import com.icse.floodguard.ui.theme.lightBlue

@Composable
fun Home(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()
        .background(Brush.verticalGradient(
            listOf(DarkBlue, lightBlue)
        )

        )
        .padding(
            10.dp, 10.dp
        )

    ){
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.12f)
            ),
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth().align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "You have successfully logged in!",
                    color = Color.LightGray,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login"){
                        popUpTo(0)
                    }

                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan
                    )
                ) {
                    Text(
                        text = "Logout"
                    )
                }
            }
        }
    }

}