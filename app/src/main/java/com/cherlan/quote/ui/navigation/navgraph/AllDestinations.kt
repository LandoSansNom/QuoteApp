package com.cherlan.quote.ui.navigation.navgraph

import androidx.navigation.NavHostController
import com.cherlan.quote.ui.navigation.navgraph.AllDestinations.Home

object AllDestinations {
    const val Home = "Home"
}

class AppNavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(Home) {
            popUpTo(Home)
        }
    }
}