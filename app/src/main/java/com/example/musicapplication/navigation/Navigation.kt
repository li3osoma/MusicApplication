package com.example.musicapplication.navigation

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicapplication.presentation.auth.AuthScreen
import com.example.musicapplication.presentation.auth.authEnterAnimation
import com.example.musicapplication.presentation.auth.authExitAnimation
import com.example.musicapplication.presentation.mainScreen.MainScreen
import com.example.musicapplication.presentation.profile.ProfileScreen
import com.example.musicapplication.presentation.streamScreen.StreamMainScreen

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.AuthScreen)
}

@Composable
fun Navigation(navController: NavHostController, context: Context) {


    NavHost(navController = navController, startDestination = Screen.AuthScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
            NavigationRouter.currentScreen.value = Screen.MainScreen
        }
        composable(
            route = Screen.AuthScreen.route,
            enterTransition = {
                authEnterAnimation()
            },
            exitTransition = {
                authExitAnimation()
            },
            popEnterTransition = {
                authEnterAnimation()
            },
            popExitTransition = {
                authExitAnimation()
            }
        ){
            AnimatedVisibility(
                visible = true,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ){
                AuthScreen( navController = navController)
            }
            NavigationRouter.currentScreen.value = Screen.AuthScreen
        }
        composable(route = Screen.StreamScreen.route) {
            StreamMainScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
            NavigationRouter.currentScreen.value=Screen.ProfileScreen
        }
    }
}