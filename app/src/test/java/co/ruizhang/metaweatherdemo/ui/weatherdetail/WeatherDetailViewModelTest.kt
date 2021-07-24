package co.ruizhang.metaweatherdemo.ui.weatherdetail

import android.security.identity.ResultData
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.ruizhang.metaweatherdemo.data.api.WeatherAPI
import co.ruizhang.metaweatherdemo.data.domain.LocationWeatherData
import co.ruizhang.metaweatherdemo.data.domain.Weather
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepositoryImpl
import co.ruizhang.metaweatherdemo.data.domain.WeatherState
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import com.google.gson.GsonBuilder
import io.github.aakira.napier.Napier
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source
import org.junit.Rule
import java.nio.charset.StandardCharsets
import java.time.LocalDate


class WeatherDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(WeatherAPI::class.java)

    private val repo = WeatherDataRepositoryImpl(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should fetch Weather correctly given 200 response`() = runBlocking {
        val inputStream = javaClass.classLoader?.getResourceAsStream("get-london-weather-200.json")

        val source = inputStream?.let { inputStream.source().buffer() }
        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setResponseCode(200)
            .setBody(source!!.readString(StandardCharsets.UTF_8))

        mockWebServer.enqueue(response)
        val vm = WeatherDetailViewModel(repo)
        vm.getWeather(44418)
        val firstItem = vm.viewData.first()
        assert(firstItem is ViewResultData.Loading<*>)
        val secondItem = vm.viewData.drop(1).first()
        assert(secondItem is ViewResultData.Success<*>)
        assertEquals(secondItem.data, mockResult)
    }

    @Before
    fun setUp() {
    }


    private val mockResult = LocationWeatherData(
        title = "London",
        woeid = 44418,
        consolidatedWeathers = listOf(
            Weather(
                id = 6286069155758080,
                weatherState = WeatherState.HEAVY_CLOUD,
                windSpeed = 7.4348316f,
                applicableDate = LocalDate.parse("2021-07-23"),
                temperature = 23,
                maxTemperature = 24,
                minTemperature = 16,
                airPressure = 1017.5f,
                predictability = 71
            ),
            Weather(
                id = 4973854104485888,
                weatherState = WeatherState.LIGHT_RAIN,
                windSpeed = 6.1848555f,
                applicableDate = LocalDate.parse("2021-07-24"),
                temperature = 19,
                maxTemperature = 20,
                minTemperature = 16,
                airPressure = 1008.0f,
                predictability = 75
            ), Weather(
                id = 5073166465499136,
                weatherState = WeatherState.HEAVY_RAIN,
                windSpeed = 4.2419333f,
                applicableDate = LocalDate.parse("2021-07-25"),
                temperature = 20,
                maxTemperature = 21,
                minTemperature = 16,
                airPressure = 1007.5f,
                predictability = 77
            ), Weather(
                id = 6559998109810688,
                weatherState = WeatherState.LIGHT_RAIN,
                windSpeed = 5.11611f,
                applicableDate = LocalDate.parse("2021-07-26"),
                temperature = 24,
                maxTemperature = 26,
                minTemperature = 16,
                airPressure = 1010.0f,
                predictability = 75
            ), Weather(
                id = 5593046486876160,
                weatherState = WeatherState.LIGHT_RAIN,
                windSpeed = 5.838049f,
                applicableDate = LocalDate.parse("2021-07-27"),
                temperature = 22,
                maxTemperature = 24,
                minTemperature = 16,
                airPressure = 1008.5f,
                predictability = 75
            ), Weather(
                id = 4843901010575360, weatherState = WeatherState.HEAVY_CLOUD,
                windSpeed = 6.829898f,
                applicableDate = LocalDate.parse("2021-07-28"),
                temperature = 21,
                maxTemperature = 22,
                minTemperature = 14,
                airPressure = 1013.0f,
                predictability = 71
            )
        )
    )

}