package com.example.gs2.ui.theme.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.* // Importa tudo do material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gs2.R
import com.example.gs2.ui.theme.utils.calcularImc
import com.example.gs2.ui.theme.utils.determinarClassificacaoIMC

@Composable
fun IMCScreen(
    modifier: Modifier = Modifier,
    onNavigateBackToMenu: () -> Unit // 1. Recebendo a função de navegação
) {

    // Estados
    var nome by remember { mutableStateOf("") } // <-- NOVO ESTADO (SEU NOME)
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var statusImc by remember { mutableStateOf("Resultado") }
    var nomeResultado by remember { mutableStateOf("") } // <-- NOVO (Nome no resultado)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // ---- header ---------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(colorResource(id = R.color.vermelho_fiap))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
                )
            }
            // --- formulário
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "Seus dados",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.vermelho_fiap),
                            textAlign = TextAlign.Center
                        )

                        // --- 2. CAMPO NOME ---
                        Spacer(modifier = Modifier.height(32.dp))
                        OutlinedTextField(
                            value = nome,
                            onValueChange = { nome = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Seu nome") }, // <-- NOVO CAMPO
                            placeholder = { Text(text = "Digite seu nome.") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )

                        // --- CAMPO PESO ---
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = peso,
                            onValueChange = { peso = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Seu peso") },
                            placeholder = { Text(text = "Seu peso em Kg.") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        // --- CAMPO ALTURA ---
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = altura,
                            onValueChange = { altura = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Sua altura") },
                            placeholder = { Text(text = "Sua altura em cm.") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )

                        // --- BOTÃO CALCULAR ---
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                val pesoDouble = peso.toDoubleOrNull()
                                val alturaDouble = altura.toDoubleOrNull()

                                if (pesoDouble != null && alturaDouble != null && alturaDouble > 0) {
                                    imc = calcularImc(altura = alturaDouble, peso = pesoDouble)
                                    statusImc = determinarClassificacaoIMC(imc)
                                    nomeResultado = nome // <-- Salva o nome para o resultado
                                } else {
                                    statusImc = "Dados inválidos"
                                    imc = 0.0
                                    nomeResultado = ""
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.vermelho_fiap)
                            )
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }

                        // --- 3. BOTÃO VOLTAR ---
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { onNavigateBackToMenu() }, // <-- CHAMA A NAVEGAÇÃO
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray // Cor diferente
                            )
                        ) {
                            Text(
                                text = "VOLTAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // --- 4. Card Resultado (Atualizado) ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.weight(1f)) { // Dá peso para o texto não estourar
                    Text(
                        // Mostra o nome da pessoa junto com o status
                        text = if (nomeResultado.isNotBlank()) "$nomeResultado, seu status é:" else "Resultado",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = statusImc,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = String.format("%.1f", imc),
                    modifier = Modifier.padding(start = 16.dp), // Espaçamento
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}