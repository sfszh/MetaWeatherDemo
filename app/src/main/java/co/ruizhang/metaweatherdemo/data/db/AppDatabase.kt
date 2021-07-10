package co.ruizhang.metaweatherdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.ruizhang.metaweatherdemo.data.api.WeatherLocationApiModel

@Database(entities = [WeatherLocationDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun LocationDao() : LocationDao
}