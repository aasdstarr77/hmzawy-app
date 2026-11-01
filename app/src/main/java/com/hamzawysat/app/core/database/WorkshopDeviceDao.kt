package com.hamzawysat.app.core.database
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao interface WorkshopDeviceDao{@Query("SELECT * FROM workshop_devices ORDER BY receivedAt DESC") fun observeAll():Flow<List<WorkshopDeviceEntity>>;@Query("SELECT * FROM workshop_devices WHERE receiptNumber=:receipt LIMIT 1") suspend fun findByReceipt(receipt:String):WorkshopDeviceEntity?;@Insert(onConflict=OnConflictStrategy.ABORT) suspend fun insert(device:WorkshopDeviceEntity);@Update suspend fun update(device:WorkshopDeviceEntity)}
