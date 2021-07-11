package co.ruizhang.metaweatherdemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ruizhang.metaweatherdemo.MainDestinations.LOCATION_LIST
import co.ruizhang.metaweatherdemo.MainDestinations.WEATHER_DETAIL
import co.ruizhang.metaweatherdemo.MainDestinations.WEATHER_LOCATION_ID
import co.ruizhang.metaweatherdemo.ui.weatherdetail.WeatherDetail
import co.ruizhang.metaweatherdemo.ui.weatherlist.LocationUI

object MainDestinations {
    const val LOCATION_LIST = "location_list"
    const val WEATHER_DETAIL = "weather_detail"
    const val WEATHER_LOCATION_ID = "weather_location_id"

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
            LocationUI(selectLocation = {navController.navigate("${WEATHER_DETAIL}/$it") }, addLocation = {})
        }

        composable("${WEATHER_DETAIL}/{${WEATHER_LOCATION_ID}}") { backStackEntry ->
            val woeid = backStackEntry.arguments?.getString(WEATHER_LOCATION_ID)?.toInt() ?: 0
            WeatherDetail(woeid, back = { navController.popBackStack() })
        }
    }
}
