package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.ruizhang.metaweatherdemo.ui.theme.MetaWeatherDemoTheme
import com.google.accompanist.coil.rememberCoilPainter
import co.ruizhang.metaweatherdemo.R
import co.ruizhang.metaweatherdemo.data.domain.Weather
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDetail(
    woeid: Int,
    back: () -> Unit,
    vm: WeatherDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier, // leave it for now
) {

    val viewData = vm.viewData.observeAsState()
    vm.getWeather(woeid)
    MetaWeatherDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "City Title") },
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
            Column {
                viewData.value?.consolidatedWeathers?.forEach {
                    DailyWeatherCard(it.toViewData())
                }
            }
        }
    }
}


@Composable
fun DailyWeatherCard(viewData: DailyWeatherViewData, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = viewData.date)
            Image(
                painter = rememberCoilPainter(viewData.imageUrl),
                contentDescription = viewData.weatherDescription,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(text = viewData.maxMinTemp)
        }

    }
}

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

