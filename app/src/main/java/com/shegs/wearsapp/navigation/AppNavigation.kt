package com.shegs.wearsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shegs.wearsapp.ui.screens.FootWearsScreen
import com.shegs.wearsapp.ui.screens.GetStartedScreen
import com.shegs.wearsapp.ui.screens.MainContent
import com.shegs.wearsapp.ui.screens.MenWearsScreen
import com.shegs.wearsapp.ui.screens.WomenWearsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onBoarding"
    ) {
        composable("tabScreen") { MainContent() }
        composable("onBoarding") { GetStartedScreen(navController) }
        composable("menWears") { MenWearsScreen() }
        composable("womenWears") { WomenWearsScreen() }
        composable("footWears") { FootWearsScreen() }
    }
}