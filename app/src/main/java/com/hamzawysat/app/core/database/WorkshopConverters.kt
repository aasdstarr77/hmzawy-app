package com.hamzawysat.app.core.database
import androidx.room.TypeConverter
class WorkshopConverters{@TypeConverter fun statusToString(value:WorkshopStatus)=value.name;@TypeConverter fun stringToStatus(value:String)=runCatching{WorkshopStatus.valueOf(value)}.getOrDefault(WorkshopStatus.RECEIVED)}
