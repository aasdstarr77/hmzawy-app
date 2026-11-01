package com.hamzawysat.app.core.network
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
data class ApiEnvelope<T>(val success:Boolean,val data:T?=null,val message:String?=null)
data class CreateOrderRequest(val serviceType:String,val fulfillmentType:String,val answers:Map<String,String>,val customerName:String,val customerPhone:String,val address:Map<String,String>?=null)
data class WorkshopLookupDto(val receiptNumber:String,val status:String,val estimatedCostPiasters:Long,val finalCostPiasters:Long)
interface ApiService{@GET("health") suspend fun health():ApiEnvelope<Map<String,String>>;@POST("api/orders") suspend fun createOrder(@Body request:CreateOrderRequest):ApiEnvelope<Map<String,String>>;@GET("api/workshop/receipt/{receipt}") suspend fun getWorkshopTicket(@Path("receipt") receipt:String):ApiEnvelope<WorkshopLookupDto>}
