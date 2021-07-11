package co.ruizhang.metaweatherdemo.data.domain

import co.ruizhang.metaweatherdemo.data.api.LocationWeatherDataApiModel
import co.ruizhang.metaweatherdemo.data.api.WeatherAPI
import co.ruizhang.metaweatherdemo.data.api.WeatherApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface WeatherDataRepository {
    val weatherData: Flow<HashMap<Int, LocationWeatherData>> //woeid, LocationWeatherData
    suspend fun update(woeid: Int)
}


class WeatherDataRepositoryImpl @Inject constructor(val api: WeatherAPI) : WeatherDataRepository {

    private val weatherDataCache = MutableSharedFlow<HashMap<Int, LocationWeatherData>>(1)

    override val weatherData: Flow<HashMap<Int, LocationWeatherData>>
        get() = weatherDataCache

    override suspend fun update(woeid: Int) {
        withContext(Dispatchers.IO) {
            try {
                val domainModel = api.getWeather(woeid).body()!!.toDomain()
                val existedData = weatherDataCache.lastOrNull() ?: hashMapOf()
                existedData[domainModel.woeid] = domainModel
                weatherDataCache.emit(existedData)
            } catch (e: Exception) {
                //todo add error handler
            }

        }
    }
}

private fun LocationWeatherDataApiModel.toDomain(): LocationWeatherData {
    return LocationWeatherData(
        title = title,
        woeid = woeid,
        consolidatedWeathers = consolidated_weather.map { it.toDomain() }
    )
}

private fun WeatherApiModel.toDomain(): Weather {
    return Weather(
        id = id,
        weatherState = weather_state_name.toWeatherState(),
        windSpeed = wind_speed,
        applicableDate = applicable_date,
        temperature = temperature,
        airPressure = air_pressure,
        predictability = predictability,
    )
}

fun String.toWeatherState(): WeatherState {
    return when (this) {
        WeatherState.SNOW.stateName -> WeatherState.SNOW
        WeatherState.SLEET.stateName -> WeatherState.SLEET
        WeatherState.HAIL.stateName -> WeatherState.HAIL
        WeatherState.THUNDERSTORM.stateName -> WeatherState.THUNDERSTORM
        WeatherState.HEAVY_RAIN.stateName -> WeatherState.HEAVY_RAIN
        WeatherState.LIGHT_RAIN.stateName -> WeatherState.LIGHT_RAIN
        WeatherState.SHOWERS.stateName -> WeatherState.SHOWERS
        WeatherState.HEAVY_CLOUD.stateName -> WeatherState.HEAVY_CLOUD
        WeatherState.LIGHT_CLOUD.stateName -> WeatherState.LIGHT_CLOUD
        WeatherState.CLEAR.stateName -> WeatherState.CLEAR
        else -> throw IllegalArgumentException("Unknown State $this")
    }
}
