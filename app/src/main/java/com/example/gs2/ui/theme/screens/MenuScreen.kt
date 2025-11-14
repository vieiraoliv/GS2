package com.example.gs2.ui.theme.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    onNavigateToBmi: () -> Unit,
    onNavigateToTeam: () -> Unit,
    onNavigateBackToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MENU",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )


        Button(
            onClick = { onNavigateToBmi() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cálculo de IMC")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToTeam() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Equipe")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavigateBackToLogin() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voltar")
        }
    }
}