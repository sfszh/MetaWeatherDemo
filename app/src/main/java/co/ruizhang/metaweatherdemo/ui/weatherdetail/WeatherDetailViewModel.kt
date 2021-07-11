package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationWeatherData
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(repo: WeatherDataRepository) : ViewModel() {
    private val getEvent = MutableSharedFlow<Int>(1)

    val viewData: LiveData<LocationWeatherData> =
        getEvent.distinctUntilChanged()
            .combine(repo.weatherData.onStart { emit(hashMapOf()) }) { woeid, weatherData ->
                val data = weatherData[woeid]
                if (data == null) {
                    repo.update(woeid)
                }
                data
            }.filterNotNull().asLiveData()


    fun getWeather(woeid: Int) {
        viewModelScope.launch {
            getEvent.emit(woeid)
        }
    }
}