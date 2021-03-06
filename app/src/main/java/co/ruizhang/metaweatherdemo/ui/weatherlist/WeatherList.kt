package co.ruizhang.metaweatherdemo.ui.weatherlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import co.ruizhang.metaweatherdemo.data.domain.MOCK_LOCATION_VIEWDATA
import co.ruizhang.metaweatherdemo.ui.ViewResultData
import co.ruizhang.metaweatherdemo.ui.theme.MetaWeatherDemoTheme

@Composable
fun LocationUI(
    modifier: Modifier = Modifier,
    vm: LocationListViewModel = hiltViewModel(),
    selectLocation: (Int) -> Unit,
    addLocation: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewDataFlowLifecycleAware = remember(vm.locations, lifecycleOwner) {
        vm.locations.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val viewDataState = viewDataFlowLifecycleAware.collectAsState(null)
    LocationUI(
        modifier,
        viewDataState,
        selectLocation,
        addLocation
    )
}

@Composable
private fun LocationUI(
    modifier: Modifier = Modifier,
    dataState: State<ViewResultData<List<LocationViewData>>?>,
    selectLocation: (Int) -> Unit,
    addLocation: () -> Unit
) {
    MetaWeatherDemoTheme {
        Scaffold(
            floatingActionButton = {
                AddRepoButton {
                    addLocation()
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) }
                )
            }
        ) { _ ->
            when(dataState.value) {
                is ViewResultData.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = modifier
                    ) {
                        dataState.value?.data?.let {
                            items(it) { location ->
                                LocationCard(
                                    location = location,
                                    onClick = { selectLocation(location.woeid) },
                                    modifier = modifier
                                )
                            }
                        }
                    }
                }
                is ViewResultData.Error -> {
                    Snackbar(action = {
                        Text(text = stringResource(id = R.string.okay), style = TextStyle(color = MaterialTheme.colors.secondary))
                    }) {
                        Text(text = stringResource(id = R.string.loading_error))
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
            }
        }
    }
}


@Composable
fun LocationCard(
    location: LocationViewData,
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
                text = location.woeid.toString(),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun AddRepoButton(add: () -> Unit) {
    FloatingActionButton(
        onClick = add,
    ) {
        Text(text = "+")
    }
}


@Preview("locations Preview")
@Composable
fun LocationUIPreview() {
    LocationUI(
        dataState = remember { mutableStateOf(ViewResultData.Success(MOCK_LOCATION_VIEWDATA)) },
        selectLocation = {},
        addLocation = {})
}


@Preview("Location Card Preview")
@Composable
fun LocationCardPreview() {
    val mockData = MOCK_LOCATION_VIEWDATA[0]

    MetaWeatherDemoTheme {
        LocationCard(location = mockData, onClick = {})
    }
}