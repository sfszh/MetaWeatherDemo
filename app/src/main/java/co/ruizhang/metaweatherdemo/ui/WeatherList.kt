package co.ruizhang.metaweatherdemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.ruizhang.metaweatherdemo.data.domain.MOCK_LOCATION
import co.ruizhang.metaweatherdemo.data.domain.WeatherLocation
import co.ruizhang.metaweatherdemo.ui.theme.MetaWeatherDemoTheme

@Composable
fun CityCard(
    location: WeatherLocation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // leave it for now
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Text(text = location.title, style = MaterialTheme.typography.subtitle2)
            Text(
                text = location.consolidatedWeathers.firstOrNull()?.temperature?.let { "$it \u2103" } ?: "N/A",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview("Repo Card Preview")
@Composable
fun RepoCardPreview() {
    val mockData = MOCK_LOCATION[0]

    MetaWeatherDemoTheme {
        CityCard(location = mockData, onClick = {})
    }
}