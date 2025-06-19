package com.jamiescode.showcase.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jamiescode.showcase.navigation.AppNavigator
import com.jamiescode.showcase.navigation.AppUrls
import com.jamiescode.showcase.navigation.Destinations
import com.jamiescode.showcase.navigation.launchCustomTabs
import com.jamiescode.showcase.navigation.launchOpenSourceLicenses
import com.jamiescode.showcase.presentation.compose.customTopAppBar
import com.jamiescode.showcase.theme.showcaseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navigationEvents =
                appNavigator.navigationEventsLiveData.asFlow().collectAsState(
                    initial = Destinations.Nowhere,
                )
            when (val destination = navigationEvents.value) {
                Destinations.Nowhere -> {} // Do nothing
                Destinations.OpenSourceLicenses -> {
                    LocalContext.current.launchOpenSourceLicenses()
                }
                Destinations.FeedbackForm -> {
                    LocalContext.current.launchCustomTabs(AppUrls.FEEDBACK_FORM)
                }
                Destinations.BuyMeACoffee -> {
                    LocalContext.current.launchCustomTabs(AppUrls.BUY_ME_A_COFFEE)
                }
                else -> {
                    navController.navigate(destination.route)
                }
            }
            showcaseTheme {
                Scaffold(
                    topBar = {
                        Column {
                            customTopAppBar(appNavigator = appNavigator)
                            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                        }
                    },
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Destinations.Gratitude.route,
                            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
                            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) },
                            builder = { createNavigationRoutes() },
                        )
                    }
                }
            }
        }
    }
}
