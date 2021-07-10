package co.ruizhang.metaweatherdemo.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.metaweatherdemo.data.domain.LocationRepository
import co.ruizhang.metaweatherdemo.data.domain.WeatherLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    val locations: LiveData<List<LocationViewData>> = repo.locations.map { list ->
        list.map {
            LocationViewData(
                it.woeid,
                it.title,
                0
            )
        }
    }.asLiveData()

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
    val temperature: Int
)