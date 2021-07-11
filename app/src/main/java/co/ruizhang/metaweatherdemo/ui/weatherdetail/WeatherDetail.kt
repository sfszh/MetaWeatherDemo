package co.ruizhang.metaweatherdemo.ui.weatherdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.ruizhang.metaweatherdemo.data.domain.MOCK_LOCATION_VIEWDATA
import co.ruizhang.metaweatherdemo.ui.weatherlist.LocationCard
import co.ruizhang.metaweatherdemo.ui.theme.MetaWeatherDemoTheme
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.LoadPainter
import co.ruizhang.metaweatherdemo.R

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
            val state = viewData.value?.consolidatedWeathers?.first()?.weatherState
            val vd = DailyWeatherViewData(
                state?.getImageUrl() ?: "",
                state?.stateName ?: "")
            Column {
                Text("hahahahah")
                DailyWeatherCard(vd)

            }
        }
    }
}


@Composable
fun DailyWeatherCard(viewData: DailyWeatherViewData, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier.size(width = 400.dp, height = 100.dp)
    ) {
        Column {

            Image(
                painter = rememberCoilPainter(viewData.imageUrl),
                contentDescription = viewData.contentDescription,
                modifier = Modifier
            )
        }

    }
}

data class DailyWeatherViewData(
    val imageUrl: String,
    val contentDescription: String,
)


@Preview("Daily Weather Card Preview")
@Composable
fun DailyWeatherCardPreview() {
    val mockData =
        DailyWeatherViewData("https://www.metaweather.com/static/img/weather/png/64/sn.png", "Snow")

    MetaWeatherDemoTheme {
        DailyWeatherCard(mockData)
    }
}

