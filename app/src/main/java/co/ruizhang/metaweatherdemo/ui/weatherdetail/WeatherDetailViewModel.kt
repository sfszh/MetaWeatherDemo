package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationWeatherData
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepository
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(val repo: WeatherDataRepository) : ViewModel() {
    private val getEvent = MutableSharedFlow<Int>(1)

    @ExperimentalCoroutinesApi
    val viewData: StateFlow<ViewResultData<LocationWeatherData>> = getEvent.distinctUntilChanged()
        .flatMapLatest {
            repo.getWeatherData(it)
        }
        .map {
            ViewResultData.Success(it) as ViewResultData<LocationWeatherData>
        }
        .catch { throwable ->
            emit(ViewResultData.Error(null, throwable))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ViewResultData.Loading(null)
        )

    fun getWeather(woeid: Int) {
        viewModelScope.launch {
            getEvent.emit(woeid)
        }

    }
}
