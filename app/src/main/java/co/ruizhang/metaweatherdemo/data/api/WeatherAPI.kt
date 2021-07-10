package co.ruizhang.metaweatherdemo.data.api

import com.google.gson.annotations.Expose
import kotlinx.datetime.LocalDate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/api/location/{woeid}}/")
    suspend fun getWeather(@Path("woeid") woeid: Int): Response<WeatherDataApiModel>
    @GET("/api/location/search/")
    suspend fun searchLocation(@Query("query")title : String) : Response<List<WeatherLocationApiModel>>
}

data class WeatherApiModel(
    @Expose
    val id: Int,
    @Expose
    val weather_state_name: String,
    @Expose
    val windSpeed: Float, //mph
    @Expose
    val applicable_date: LocalDate,
    @Expose
    val temperature: Int, //centigrade
    @Expose
    val air_pressure: Float, //mbar
    @Expose
    val predictability: Int, //percentage
)

data class WeatherDataApiModel(
    @Expose
    val title: String,
    @Expose
    val woeid: Int,
    @Expose
    val consolidated_weather: List<WeatherApiModel>,
)

data class WeatherLocationApiModel(
    @Expose
    val title: String,
    @Expose
    val woeid: Int
)