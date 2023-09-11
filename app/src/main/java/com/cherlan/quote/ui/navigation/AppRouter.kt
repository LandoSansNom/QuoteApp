package com.cherlan.quote.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.cherlan.quote.data.repository.Repository


sealed class Screen {
    object RegistrationScreen : Screen()
    object Login : Screen()
    object Home: Screen()
}


object AppRouter {

    lateinit var repository: Repository
    private var _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Login)

    fun currentScreen(): MutableState<Screen> {
        _currentScreen =
            if (this::repository.isInitialized) {
                if(repository.currentUser != null){
                    mutableStateOf(Screen.Home)
                }else{
                    mutableStateOf(Screen.Login)
                }
            }else{
                mutableStateOf(Screen.Login)
            }
        return _currentScreen
    }

    fun navigateTo(destination: Screen){
        _currentScreen.value = destination
    }

    fun with(repository: Repository): AppRouter {
        AppRouter.repository = repository
        return this
    }
}