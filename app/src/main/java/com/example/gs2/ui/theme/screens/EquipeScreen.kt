package com.example.gs2.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
fun EquipeScreen(
    modifier: Modifier = Modifier,
    onNavigateBackToMenu: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "EQUIPE", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Integrantes:", fontSize = 18.sp)
        Text(text = "Henrique Vieira 558777")
        Text(text = "Ygor Arean 8870")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { onNavigateBackToMenu() }) {
            Text(text = "Voltar para o Menu")
        }
    }
}