package co.ruizhang.metaweatherdemo.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM weather_location_table")
    fun getLocations() : Flow<List<WeatherLocationDbModel>>
    @Insert
    suspend fun insertLocations(repos: List<WeatherLocationDbModel>)

    @Insert
    suspend fun insertLocation(vararg repos: WeatherLocationDbModel)

    @Delete
    suspend fun delete(repo: WeatherLocationDbModel)

}

@Entity(tableName = "weather_location_table")
data class WeatherLocationDbModel(
    val title: String,
    @PrimaryKey
    val woeid: Int
)