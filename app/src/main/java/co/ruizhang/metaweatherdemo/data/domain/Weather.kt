package co.ruizhang.metaweatherdemo.data.domain

import kotlinx.datetime.LocalDate

data class Weather(
    val id: Int,
    val weatherState: WeatherState,
    val windSpeed: Float, //mph
    val applicable_date: LocalDate,
    val temperature: Int, //centigrade
    val airPressure: Float, //mbar
    val predictability: Int, //percentage
)

data class WeatherLocation(
    val title: String,
    val woeid: Int,
    val consolidatedWeathers: List<Weather>,
)

enum class WeatherState(val stateName: String, val stateAbbr: String) {
    SNOW("snow", "sn"),
    SLEET("Sleet", "sl"),
    HAIL("Hail", "h"),
    THUNDERSTORM("Thunderstorm", "t"),
    HEAVY_RAIN("Heavy Rain", "hr"),
    LIGHT_RAIN("Light Rain", "lr"),
    SHOWERS("Showers", "s"),
    HEAVY_CLOUD("Heavy Cloud", "hc"),
    LIGHT_CLOUD("Light Cloud", "lc"),
    CLEAR("Clear", "c");
}