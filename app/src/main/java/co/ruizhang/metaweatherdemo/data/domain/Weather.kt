package co.ruizhang.metaweatherdemo.data.domain

import java.time.LocalDate


data class Weather(
    val id: Long,
    val weatherState: WeatherState,
    val windSpeed: Float, //mph
    val applicableDate: LocalDate,
    val temperature: Int, //centigrade
    val maxTemperature: Int,
    val minTemperature:Int,
    val airPressure: Float, //mbar
    val predictability: Int, //percentage
)

data class LocationWeatherData(
    val title: String,
    val woeid: Int,
    val consolidatedWeathers: List<Weather>,
)

enum class WeatherState(val stateName: String, val stateAbbr: String) {
    SNOW("Snow", "sn"),
    SLEET("Sleet", "sl"),
    HAIL("Hail", "h"),
    THUNDERSTORM("Thunderstorm", "t"),
    HEAVY_RAIN("Heavy Rain", "hr"),
    LIGHT_RAIN("Light Rain", "lr"),
    SHOWERS("Showers", "s"),
    HEAVY_CLOUD("Heavy Cloud", "hc"),
    LIGHT_CLOUD("Light Cloud", "lc"),
    CLEAR("Clear", "c");


    fun getImageUrl() : String {
        return "https://www.metaweather.com/static/img/weather/png/64/$stateAbbr.png"
    }
}