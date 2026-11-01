package com.hamzawysat.app.core.database
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID
@Entity(tableName="workshop_devices",indices=[Index(value=["receiptNumber"],unique=true)])
data class WorkshopDeviceEntity(@PrimaryKey val id:String=UUID.randomUUID().toString(),val receiptNumber:String,val customerName:String,val customerPhone:String,val deviceType:String,val deviceModel:String,val serialNumber:String?=null,val problemDescription:String,val externalCondition:String="",val accessories:String="",val status:WorkshopStatus=WorkshopStatus.RECEIVED,val estimatedCostPiasters:Long=0,val finalCostPiasters:Long=0,val isPaid:Boolean=false,val warrantyMonths:Int=0,val receivedAt:Long=System.currentTimeMillis(),val deliveredAt:Long?=null,val technicianNotes:String="")
enum class WorkshopStatus{RECEIVED,INSPECTION,WAITING_CUSTOMER_APPROVAL,IN_REPAIR,READY_FOR_PICKUP,DELIVERED}
