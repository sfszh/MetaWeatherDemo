package co.ruizhang.metaweatherdemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ruizhang.metaweatherdemo.MainDestinations.LOCATION_LIST
import co.ruizhang.metaweatherdemo.ui.LocationUI

object MainDestinations {
    const val LOCATION_LIST = "location_list"
    const val WEATHER = "weather"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = LOCATION_LIST,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(LOCATION_LIST) {
            LocationUI(selectLocation = {}, addLocation = {})
        }
    }
}
