package co.ruizhang.metaweatherdemo.data.domain

import kotlinx.datetime.LocalDate


val MOCK_LOCATION = listOf(
    WeatherLocation(
        title = "LOC0",
        woeid = 0,
        consolidatedWeathers = listOf(
            Weather(
                id = 0,
                weatherState = WeatherState.CLEAR,
                windSpeed = 0f,
                applicable_date = LocalDate.parse("2020-12-22"),
                temperature = 0,
                airPressure = 9f,
                predictability = 90
            ),
            Weather(
                id = 0,
                weatherState = WeatherState.HAIL,
                windSpeed = 0f,
                applicable_date = LocalDate.parse("2020-12-23"),
                temperature = 0,
                airPressure = 9f,
                predictability = 90
            )
        )
    ),
    WeatherLocation(
        title = "LOC1",
        woeid = 1,
        consolidatedWeathers = listOf(
            Weather(
                id = 0,
                weatherState = WeatherState.CLEAR,
                windSpeed = 0f,
                applicable_date = LocalDate.parse("2020-12-22"),
                temperature = 0,
                airPressure = 9f,
                predictability = 90
            ),
            Weather(
                id = 0,
                weatherState = WeatherState.HAIL,
                windSpeed = 0f,
                applicable_date = LocalDate.parse("2020-12-23"),
                temperature = 0,
                airPressure = 9f,
                predictability = 90
            )
        )
    )
)