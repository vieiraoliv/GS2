package com.example.gs2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gs2.ui.theme.GS2Theme
import com.example.gs2.ui.theme.screens.LoginScreen
import com.example.gs2.ui.theme.screens.MenuScreen
import com.example.gs2.ui.theme.screens.IMCScreen
import com.example.gs2.ui.theme.screens.EquipeScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GS2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // 1. Cria o Controlador de Navegação
                    val navController = rememberNavController()

                    // 2. Define o NavHost (o "mapa" da sua aplicação)
                    NavHost(
                        navController = navController,
                        startDestination = "login", // O app começa na tela de login
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        // --- Rota para a Tela de Login ---
                        composable(route = "login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    // Se o login der certo, vai para o menu
                                    // e apaga a tela de login do histórico
                                    navController.navigate("menu") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // --- Rota para a Tela de Menu ---
                        composable(route = "menu") {
                            MenuScreen(
                                onNavigateToBmi = { navController.navigate("imc") },
                                onNavigateToTeam = { navController.navigate("equipe") },
                                onNavigateBackToLogin = {
                                    // Volta para o login e apaga o menu do histórico
                                    navController.navigate("login") {
                                        popUpTo("menu") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // --- Rota para a Tela de IMC ---
                        composable(route = "imc") {
                            IMCScreen(
                                onNavigateBackToMenu = {
                                    navController.popBackStack() // Apenas volta para a tela anterior
                                }
                            )
                        }

                        // --- Rota para a Tela de Equipe ---
                        composable(route = "equipe") {
                            EquipeScreen(
                                onNavigateBackToMenu = {
                                    navController.popBackStack() // Apenas volta para a tela anterior
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}