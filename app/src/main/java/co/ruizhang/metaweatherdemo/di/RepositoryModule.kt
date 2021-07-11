package co.ruizhang.metaweatherdemo.di

import co.ruizhang.metaweatherdemo.data.api.WeatherAPI
import co.ruizhang.metaweatherdemo.data.db.LocationDao
import co.ruizhang.metaweatherdemo.data.domain.LocationRepository
import co.ruizhang.metaweatherdemo.data.domain.LocationRepositoryImpl
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepository
import co.ruizhang.metaweatherdemo.data.domain.WeatherDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideLocationRepository(api: WeatherAPI, dao: LocationDao): LocationRepository {
        return LocationRepositoryImpl(api, dao)
    }

    @Singleton
    @Provides
    fun provideWeatherDataRepository(api: WeatherAPI): WeatherDataRepository {
        return WeatherDataRepositoryImpl(api)
    }

}