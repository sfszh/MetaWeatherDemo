package co.ruizhang.metaweatherdemo.data.domain

import co.ruizhang.metaweatherdemo.data.api.WeatherAPI
import co.ruizhang.metaweatherdemo.data.api.WeatherLocationApiModel
import co.ruizhang.metaweatherdemo.data.db.LocationDao
import co.ruizhang.metaweatherdemo.data.db.WeatherLocationDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LocationRepository {
    val locations: Flow<List<WeatherLocation>>
    suspend fun searchLocation(title: String)
}

class LocationRepositoryImpl @Inject constructor(
    private val api: WeatherAPI,
    private val dao: LocationDao
) : LocationRepository {
    override val locations: Flow<List<WeatherLocation>>
        get() = dao.getLocations().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun searchLocation(title: String) {
        return withContext(Dispatchers.IO) {
            val dbModels = try {
                api.searchLocation(title).body()?.map{it.toDbModel() }
            } catch (e: Exception) {
                null
            }
            dbModels?.let { dao.insertLocations(it) }
        }
    }
}

data class WeatherLocation(
    val title: String,
    val woeid: Int
)

private fun WeatherLocationApiModel.toDbModel(): WeatherLocationDbModel {
    return WeatherLocationDbModel(title, woeid)
}

private fun WeatherLocationDbModel.toDomain(): WeatherLocation {
    return WeatherLocation(title, woeid)
}