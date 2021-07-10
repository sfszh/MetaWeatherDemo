package co.ruizhang.metaweatherdemo.di

import android.content.Context
import androidx.room.Room
import co.ruizhang.metaweatherdemo.data.db.AppDatabase
import co.ruizhang.metaweatherdemo.data.db.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase) : LocationDao {
        return appDatabase.LocationDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}