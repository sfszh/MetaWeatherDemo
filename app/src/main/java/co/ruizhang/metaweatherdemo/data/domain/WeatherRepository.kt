package co.ruizhang.metaweatherdemo.data.domain

import co.ruizhang.metaweatherdemo.data.api.WeatherAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeatherDataRepository {
    fun getall() : Flow<List<LocationWeatherData>>
    fun update(woeid: Int)
}


class WeatherDataRepositoryImpl @Inject constructor( val api: WeatherAPI) {

}


