package com.swayy.tripitaca

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.swayy.compose_ui.theme.AppTheme
import com.swayy.compose_ui.theme.TripitacaTheme
import com.swayy.core.auth.LoginScreen
import com.swayy.core.auth.SplashScreen
import com.swayy.core.core.PreferencesConstants
import com.swayy.core.data.datastore.ThemeSettingsManager
import com.swayy.core.util.Route
import com.swayy.core.viewmodel.ConnectWalletViewModel
import com.swayy.home.presentation.screens.HomeScreen
import com.swayy.home.presentation.screens.ListingDetailScreen
import com.swayy.more.settings.SettingsScreen
import com.swayy.tripitaca.ui.components.animatedComposable
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val mainViewModel: MainActivityViewModel = hiltViewModel()

            val viewModel: ConnectWalletViewModel = hiltViewModel()

            val dynamicColors by mainViewModel.dc.collectAsStateWithLifecycle(isSystemInDarkTheme())
            val darkTheme by mainViewModel.darkTheme.collectAsStateWithLifecycle(
                PreferencesConstants.DEFAULT_DARK_THEME
            )
            val amoledBlack by mainViewModel.amoledBlack.collectAsStateWithLifecycle(
                PreferencesConstants.DEFAULT_AMOLED_BLACK
            )
            val currentTheme by mainViewModel.currentTheme.collectAsStateWithLifecycle(
                PreferencesConstants.DEFAULT_SELECTED_THEME
            )

            val walletAddress = viewModel.userWallet.collectAsState().value

            TripitacaTheme(
                darkTheme = when (darkTheme) {
                    1 -> false
                    2 -> true
                    else -> isSystemInDarkTheme()
                },
                dynamicColor = dynamicColors,
                amoled = amoledBlack,
                appTheme = when (currentTheme) {
                    PreferencesConstants.GREEN_THEME_KEY -> AppTheme.Green
                    PreferencesConstants.BLUE_THEME_KEY -> AppTheme.Blue
                    PreferencesConstants.PEACH_THEME_KEY -> AppTheme.Peach
                    PreferencesConstants.YELLOW_THEME_KEY -> AppTheme.Yellow
                    PreferencesConstants.LAVENDER_THEME_KEY -> AppTheme.Lavender
                    PreferencesConstants.BLACK_AND_WHITE_THEME_KEY -> AppTheme.BlackAndWhite
                    else -> AppTheme.Green
                }
            ) {

                val navController = rememberAnimatedNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                var bottomBarState by rememberSaveable { mutableStateOf(false) }

                LaunchedEffect(navBackStackEntry) {
                    bottomBarState = when (navBackStackEntry?.destination?.route) {
                        Route.HOME, Route.SETTINGS -> true
                        else -> false
                    }
                }
                CompositionLocalProvider() {
                    Scaffold(
//                        bottomBar = {
//                            NavigationBar(
//                                navController = navController,
//                                bottomBarState = bottomBarState
//                            )
//                        },
                        contentWindowInsets = WindowInsets(0.dp)
                    ) { paddingValues ->
                        AnimatedNavHost(
                            navController = navController,
                            startDestination = Route.SPLASH,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            animatedComposable(Route.HOME) {
                                HomeScreen(
                                    navigateListingDetails = { listing,name ->
                                        navController.navigate(
                                            "listing/${listing}/${name}"
                                        )
                                    },
                                    navigateSettings = {
                                        navController.navigate("settings/?fromGame=false")
                                    }
                                )
                            }

                            animatedComposable(Route.SETTINGS) {
//                                MoreScreen(
//                                    navigateSettings = { navController.navigate("settings/?fromGame=false") }
//                                )
                            }

                            animatedComposable(Route.LOGIN) {
                                LoginScreen(
                                    navigateHome = {
                                        navController.navigate(
                                            "home"
                                        )
                                    }
                                )
                            }

                            animatedComposable(Route.SPLASH) {
                                SplashScreen(
                                    navigateHome = {
                                        navController.navigate(
                                            "home"
                                        )
                                    },
                                    navigateLogin = {
                                        navController.navigate(
                                            "login"
                                        )
                                    }
                                )
                            }

                            animatedComposable(
                                route = Route.SETTINGS,
                                arguments = listOf(navArgument("fromGame") {
                                    defaultValue = false
                                    type = NavType.BoolType
                                })
                            ) {
                                SettingsScreen(
                                    navigateBack = { navController.popBackStack() },
                                    hiltViewModel(),
                                    navigateBoardSettings = { navController.navigate("settings_board_theme") }
                                )

                            }

                            animatedComposable(
                                route = Route.LISTING_DETAIL,
                                arguments = listOf(
                                    navArgument("listing") { type = NavType.StringType },
                                    navArgument("name") { type = NavType.StringType },
                                )
                            ) {
                                val arguments = requireNotNull(it.arguments)
                                val listing = arguments.getString("listing")
                                val name = arguments.getString("name")
                                ListingDetailScreen(
                                    navigateBack = { navController.popBackStack() },
                                    listing = listing!!,
                                    name = name!!
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationBar(
    navController: NavController,
    bottomBarState: Boolean
) {
    var selectedScreen by remember { mutableStateOf(Route.HOME) }
    val navBarScreens = listOf(
        Pair(Route.HOME, R.string.Home),
        Pair(Route.SETTINGS, R.string.Settings),
    )
    val navBarIcons = listOf(
        painterResource(com.swayy.core.R.drawable.baseline_home_24),
        painterResource(com.swayy.core.R.drawable.baseline_settings_24),
    )
    AnimatedContent(
        targetState = bottomBarState
    ) { visible ->
        if (visible) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            LaunchedEffect(currentDestination) {
                currentDestination?.let {
                    selectedScreen = it.route ?: ""
                }
            }

            androidx.compose.material3.NavigationBar(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary,
                tonalElevation = 4.dp,
                modifier = Modifier.shadow(10.dp)
            ) {
                navBarScreens.forEachIndexed { index, item ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        icon = {
                            Icon(
                                painter = navBarIcons[index],
                                contentDescription = null,
                            )
                        },
                        selected = selectedScreen == item.first,
                        label = {
                            Text(
                                text = stringResource(item.second),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        onClick = {
                            navController.navigate(item.first) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    themeSettingsManager: ThemeSettingsManager
) : ViewModel() {

    val dc = themeSettingsManager.dynamicColors
    val darkTheme = themeSettingsManager.darkTheme
    val amoledBlack = themeSettingsManager.amoledBlack
    val currentTheme = themeSettingsManager.currentTheme
}