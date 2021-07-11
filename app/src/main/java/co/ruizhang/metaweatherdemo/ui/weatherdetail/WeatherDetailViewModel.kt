package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationWeatherData
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(val repo: WeatherDataRepository) : ViewModel() {
    private val getEvent = MutableSharedFlow<Int>(1)

//    val viewData: LiveData<LocationWeatherData?> =
//        getEvent
//            .combine(repo.weatherData.onStart { emit(hashMapOf()) }) { woeid, weatherData ->
//               return@combine weatherData[woeid]
//            }
//            .asLiveData()
//            .onEach { (data, woeid) ->
//                if (data == null) {
//                    repo.update(woeid)
//                }
//                Napier.i("haha")
//            }
//            .map {
//                it.first
//            }.asLiveData()

    val viewData: LiveData<LocationWeatherData?> =
        repo.weatherData
            .map {
                it.values.firstOrNull()
            }
            .asLiveData()


    fun getWeather(woeid: Int) {
        repo.update(44418)
    }
}