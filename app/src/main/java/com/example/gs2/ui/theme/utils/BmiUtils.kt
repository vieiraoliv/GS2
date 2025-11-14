package com.example.gs2.ui.theme.utils

import kotlin.math.pow

fun calcularImc(peso: Double, altura: Double): Double {
    val alturaEmMetros = altura / 100.0
    return peso / alturaEmMetros.pow(2)
}

fun determinarClassificacaoIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 24.9 -> "Peso ideal"
        imc < 29.9 -> "Levemente acima do peso"
        imc < 34.9 -> "Obesidade Grau I"
        imc < 39.9 -> "Obesidade Grau II"
        else -> "Obesidade Grau III (Mórbida)"
    }
}