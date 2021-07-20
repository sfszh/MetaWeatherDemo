package co.ruizhang.metaweatherdemo.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationRepository
import co.ruizhang.metaweatherdemo.data.domain.WeatherLocation
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(repo: LocationRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            PREFILLED_LOCATIONS.forEach {
                repo.searchLocation(it)
            }
        }
    }

    val locations: StateFlow<ViewResultData<List<LocationViewData>>> = repo.locations
        .map { list ->
            val viewDataList = list.map {
                LocationViewData(
                    it.woeid,
                    it.title,
                )
            }
            return@map ViewResultData.Success(viewDataList) as ViewResultData<List<LocationViewData>>
        }
        .catch { throwable ->
            emit(ViewResultData.Error(null, throwable))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ViewResultData.Loading(null)
        )

    companion object {
        private val PREFILLED_LOCATIONS = listOf(
            "Gothenburg",
            "Stockholm",
            "Mountain View",
            "London",
            "New York",
            "Berlin",
        )
    }
}

data class LocationViewData(
    val woeid: Int,
    val title: String,
)