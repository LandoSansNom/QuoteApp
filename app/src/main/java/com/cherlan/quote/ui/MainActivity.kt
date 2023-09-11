package com.cherlan.quote.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.lifecycle.ViewModel
import com.cherlan.quote.data.repository.Repository
import com.cherlan.quote.ui.navigation.AppRouter
import com.cherlan.quote.ui.navigation.Screen.*
import com.cherlan.quote.ui.navigation.navgraph.NavGraph
import com.cherlan.quote.ui.quote.QuoteViewModel
import com.cherlan.quote.ui.theme.WeeklyProjectEightTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: Repository

    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeeklyProjectEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    starterScreen(context = this,
                        repository = repository,
                        mapOf("quote" to quoteViewModel)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun starterScreen(
    context: Context,
    repository: Repository,
    viewModels: Map<String, ViewModel>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        Crossfade(
            targetState = AppRouter.with(repository).currentScreen(),
            label = ""
        ) { currentState ->
            when (currentState.value) {
                is RegistrationScreen -> {
                    RegistrationScreen(context = context, repository = repository)
                }

                is Login -> {
                    Login(context = context, repository = repository)
                }

                is Home -> {
                    NavGraph(
                        viewModels["quote"] as QuoteViewModel
                    )
                }
            }

        }

    }

}