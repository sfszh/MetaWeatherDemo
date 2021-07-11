package co.ruizhang.metaweatherdemo.data.domain

import co.ruizhang.metaweatherdemo.ui.weatherlist.LocationViewData
import java.time.LocalDate


val MOCK_LOCATION = listOf(
    LocationWeatherData(
        title = "LOC0",
        woeid = 0,
        consolidatedWeathers = listOf(
            Weather(
                id = 0,
                weatherState = WeatherState.CLEAR,
                windSpeed = 0f,
                applicableDate = LocalDate.parse("2020-12-22"),
                temperature = 0,
                maxTemperature = 1,
                minTemperature = -1,
                airPressure = 9f,
                predictability = 90
            ),
            Weather(
                id = 0,
                weatherState = WeatherState.HAIL,
                windSpeed = 0f,
                applicableDate = LocalDate.parse("2020-12-23"),
                temperature = 0,
                maxTemperature = 1,
                minTemperature = -1,
                airPressure = 9f,
                predictability = 90
            )
        )
    ),
    LocationWeatherData(
        title = "LOC1",
        woeid = 1,
        consolidatedWeathers = listOf(
            Weather(
                id = 0,
                weatherState = WeatherState.CLEAR,
                windSpeed = 0f,
                applicableDate = LocalDate.parse("2020-12-22"),
                temperature = 0,
                maxTemperature = 1,
                minTemperature = -1,
                airPressure = 9f,
                predictability = 90
            ),
            Weather(
                id = 0,
                weatherState = WeatherState.HAIL,
                windSpeed = 0f,
                applicableDate = LocalDate.parse("2020-12-23"),
                temperature = 0,
                maxTemperature = 1,
                minTemperature = -1,
                airPressure = 9f,
                predictability = 90
            )
        )
    )
)

val MOCK_LOCATION_VIEWDATA = listOf(
    LocationViewData(
        title = "LOC0",
        woeid = 0,
    ),
    LocationViewData(
        title = "LOC1",
        woeid = 1,
    ),
)