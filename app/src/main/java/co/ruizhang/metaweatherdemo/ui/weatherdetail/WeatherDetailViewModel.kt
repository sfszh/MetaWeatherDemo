package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationWeatherData
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepository
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(val repo: WeatherDataRepository) : ViewModel() {
    private val getEvent = MutableSharedFlow<Int>(1)

    val viewData: LiveData<ViewResultData<LocationWeatherData>> = getEvent
        .combine(repo.weatherData) { woeid, weatherData ->
            return@combine Pair(weatherData[woeid], woeid)
        }
        .onEach { (data, woeid) ->
            if (data == null) {
                repo.update(woeid)
            }
        }
        .filter { pair ->
            pair.first != null
        }
        .map {
            ViewResultData.Success(it.first) as ViewResultData<LocationWeatherData>
        }
        .onStart {
            emit(ViewResultData.Loading(null))
        }
        .catch { throwable ->
            emit(ViewResultData.Error(null, throwable))
        }
        .asLiveData()

    fun getWeather(woeid: Int) {
        viewModelScope.launch {
            getEvent.emit(woeid)
        }

    }
}
