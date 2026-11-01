package com.hamzawysat.app.core.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
@Database(entities=[WorkshopDeviceEntity::class],version=1,exportSchema=true) @TypeConverters(WorkshopConverters::class) abstract class AppDatabase:RoomDatabase(){abstract fun workshopDeviceDao():WorkshopDeviceDao}
