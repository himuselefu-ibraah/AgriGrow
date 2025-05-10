package com.ibrahim.agrigrow.navigation

import FarmerBotScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahim.agrigrow.data.AnimalHealthDatabase
import com.ibrahim.agrigrow.data.AppDatabase
import com.ibrahim.agrigrow.data.UserDatabase
import com.ibrahim.agrigrow.repository.AnimalHealthRepository
import com.ibrahim.agrigrow.repository.CropRepository
import com.ibrahim.agrigrow.repository.UserRepository
import com.ibrahim.agrigrow.ui.screens.agriculture.AgricultureGuideScreen
import com.ibrahim.agrigrow.ui.screens.agriculture.AgroToolsTrainingScreen
import com.ibrahim.agrigrow.ui.screens.agriculture.ToolScreen
import com.ibrahim.agrigrow.ui.screens.auth.LoginScreen
import com.ibrahim.agrigrow.ui.screens.auth.RegisterScreen
import com.ibrahim.agrigrow.ui.screens.cropcalendar.CropCalendarScreen
import com.ibrahim.agrigrow.ui.screens.cropcalendar.CropDetailScreen
import com.ibrahim.agrigrow.ui.screens.cropcalendar.CropScreen
import com.ibrahim.agrigrow.ui.screens.fertilizer.FertilizerCalculatorScreen
import com.ibrahim.agrigrow.ui.screens.fertilizer.FertilizerGuideScreen
import com.ibrahim.agrigrow.ui.screens.fertilizer.FertilizerScreen
import com.ibrahim.agrigrow.ui.screens.health.AnimalHealthScreen
import com.ibrahim.agrigrow.ui.screens.health.AnimalScreen
import com.ibrahim.agrigrow.ui.screens.home.HomeScreen
import com.ibrahim.agrigrow.ui.screens.irrigation.IrrigationScreen
import com.ibrahim.agrigrow.ui.screens.pest.PestDiseaseScreen
import com.ibrahim.agrigrow.ui.screens.pest.PestScreen
import com.ibrahim.agrigrow.ui.screens.preventive.PreventiveMeasuresScreen
import com.ibrahim.agrigrow.ui.screens.profile.ProfileScreen
import com.ibrahim.agrigrow.ui.screens.profit.ProfitMarginCalculatorScreen
import com.ibrahim.agrigrow.ui.screens.settings.SettingsScreen
import com.ibrahim.agrigrow.ui.screens.splash.SplashScreen
import com.ibrahim.agrigrow.ui.screens.splash.WelcomeScreen
import com.ibrahim.agrigrow.ui.screens.weatherupdates.WeatherScreen
import com.ibrahim.agrigrow.viewmodel.AnimalHealthViewModel
import com.ibrahim.agrigrow.viewmodel.AnimalHealthViewModelFactory
import com.ibrahim.agrigrow.viewmodel.AuthViewModel
import com.ibrahim.agrigrow.viewmodel.CropCalendarViewModel
import com.ibrahim.agrigrow.viewmodel.CropCalendarViewModelFactory


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    // Create dependencies manually here (simple DI)
    val context = androidx.compose.ui.platform.LocalContext.current
    val database = AppDatabase.getDatabase(context)

    //crop calendar
    val repository = CropRepository(database.cropDao())
    val viewModel: CropCalendarViewModel = viewModel(factory = CropCalendarViewModelFactory(repository))



    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        //crop
        composable(ROUT_CAL) {
            CropScreen(navController)
        }

        composable(ROUT_CALENDAR) {
            CropCalendarScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "crop_detail/{cropId}",
            arguments = listOf(navArgument("cropId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cropId = backStackEntry.arguments?.getInt("cropId")
            CropDetailScreen(cropId = cropId, navController = navController)
        }
        //end of crop

        //weather
        composable(ROUT_WEATHER) {
            WeatherScreen()
        }

        //end of weather

        //agri
        composable(ROUT_AGRICULTURE) {
            AgricultureGuideScreen(navController)
        }
        composable(ROUT_AGRO) {
            AgroToolsTrainingScreen(navController)
        }
        composable(ROUT_TOOL) {
            ToolScreen(navController)
        }

        //pest
        composable(ROUT_PEST) {
            PestDiseaseScreen(navController)
        }
        composable(ROUT_PE) {
            PestScreen(navController)
        }

        //irrigation
        composable(ROUT_IRRIGATE) {
            IrrigationScreen(navController)
        }


        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_WELCOME) {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate("Login")
                }
            )
        }

        //fertilizer
        composable(ROUT_FERTILIZER) {
            FertilizerGuideScreen(navController)
        }
        composable(ROUT_FER) {
            FertilizerScreen(navController)
        }

        //calculations
        composable(ROUT_CALC) {
            FertilizerCalculatorScreen(navController)
        }
        composable(ROUT_PROFIT) {
            ProfitMarginCalculatorScreen(navController)
        }

        //prevent
        composable(ROUT_PREVENT) {
            PreventiveMeasuresScreen(navController)
        }

        composable(ROUT_BOT) {
            FarmerBotScreen(navController)
        }

        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUT_SETTINGS) {
            SettingsScreen(navController)
        }





        //animalhealth
        composable(ROUT_ANIMAL_HEALTH) {
            val context = LocalContext.current
            val database = AnimalHealthDatabase.getDatabase(context)
            val repository = AnimalHealthRepository(database.animalHealthDao())
            val viewModel: AnimalHealthViewModel = viewModel(factory = AnimalHealthViewModelFactory(repository))

            AnimalHealthScreen(viewModel, navController)
        }
        composable(ROUT_ANIMAL) {
            AnimalScreen(navController)
        }










        // AUTHENTICATION
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)

        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
    }
}

