package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import co.ruizhang.metaweatherdemo.R
import co.ruizhang.metaweatherdemo.data.domain.Weather
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import co.ruizhang.metaweatherdemo.ui.theme.MetaWeatherDemoTheme
import com.google.accompanist.coil.rememberCoilPainter
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDetail(
    woeid: Int,
    back: () -> Unit,
    vm: WeatherDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier, // leave it for now
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewDataFlowLifecycleAware = remember(vm.viewData, lifecycleOwner) {
        vm.viewData.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val viewDataState = viewDataFlowLifecycleAware.collectAsState(null)
    val title: String = viewDataState.value?.data?.title ?: ""
    vm.getWeather(woeid)
    MetaWeatherDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title) },
                    navigationIcon = {
                        IconButton(onClick = back) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.content_description_back)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            val viewData = viewDataState.value
            when(viewData) {
                is ViewResultData.Success -> {
                    val weathers = viewData.data?.consolidatedWeathers
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        weathers?.firstOrNull()?.let {
                            MainWeatherCard(viewData = it.toMainView())
                        }
                        weathers?.forEach {
                            DailyWeatherCard(it.toViewData())
                        }
                    }
                }
                is ViewResultData.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.wrapContentWidth(
                                Alignment.CenterHorizontally
                            )
                        )
                    }
                }
                is ViewResultData.Error -> {
                    Snackbar(action = {
                        Text(text = stringResource(id = R.string.okay), style = TextStyle(color = MaterialTheme.colors.secondary))
                    }) {
                        Text(text = stringResource(id = R.string.loading_error))
                    }
                }
            }

        }
    }
}


@Composable
fun DailyWeatherCard(viewData: DailyWeatherViewData, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = viewData.date, modifier = Modifier
            )
            Image(
                painter = rememberCoilPainter(viewData.imageUrl),
                contentDescription = viewData.weatherDescription,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(
                text = viewData.maxMinTemp, modifier = Modifier
            )
        }

    }
}

@Composable
fun MainWeatherCard(viewData: MainWeatherViewData, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = viewData.temperature, style = MaterialTheme.typography.h1)
        Column {
            Text(text = "℃", style = MaterialTheme.typography.h6)
            Text(text = viewData.weatherName, style = MaterialTheme.typography.h6)
        }
    }

}

data class MainWeatherViewData(
    val temperature: String,
    val weatherName: String,
)

data class DailyWeatherViewData(
    val date: String,
    val imageUrl: String,
    val weatherDescription: String,
    val maxMinTemp: String,
) {
    companion object {
        val EMPTY = DailyWeatherViewData("", "", "", "")
    }
}

private fun Weather.toViewData(): DailyWeatherViewData {
    val pattern = DateTimeFormatter.ofPattern("MM/dd")
    val date = this.applicableDate.format(pattern)
    val url = weatherState.getImageUrl()
    val contentDescription = weatherState.stateName
    val minMax = "$maxTemperature \u2103/ $minTemperature \u2103"

    return DailyWeatherViewData(date, url, contentDescription, minMax)
}

private fun Weather.toMainView(): MainWeatherViewData {
    return MainWeatherViewData(this.temperature.toString(), this.weatherState.stateName)
}

@Preview("Daily Weather Card Preview")
@Composable
fun DailyWeatherCardPreview() {
    val mockData =
        DailyWeatherViewData(
            "3/11",
            "https://www.metaweather.com/static/img/weather/png/64/sn.png",
            "Snow",
            "35 \u2103/27 \u2103"
        )

    MetaWeatherDemoTheme {
        DailyWeatherCard(mockData)
    }
}

@Preview("Main Weather Card Preview")
@Composable
fun MainWeatherCardPreview() {
    val mockData = MainWeatherViewData(
        "30",
        "Cloudy"
    )
    MetaWeatherDemoTheme {
        MainWeatherCard(mockData)
    }
}

