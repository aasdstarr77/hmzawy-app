package com.hamzawysat.app.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private data class Service(val title:String,val description:String,val questions:List<String>)
private val services=listOf(
 Service("الدش والرسيفرات","تركيب وضبط وصيانة الأطباق والرسيفرات وحل مشاكل الإشارة والتقطيع.",listOf("نوع الطلب: تركيب / ضبط / برمجة / صيانة","حجم الطبق: 60 / 70 / 100 / مش عارف","نوع الرسيفر: عادي / HD / 4K / عندي","الدور ومكان السطح","هل السلك واصل؟")),
 Service("كاميرات المراقبة","تركيب وصيانة أنظمة IP وAnalog وربط المشاهدة بالموبايل.",listOf("نوع الطلب: سيستم جديد / إضافة / صيانة","عدد الكاميرات","نوع المكان","داخلي أم خارجي؟","أيام التسجيل المطلوبة","هل يوجد إنترنت؟","المساحة المتاحة")),
 Service("صيانة الشاشات","صيانة شاشات LED وLCD وOLED في المنزل أو داخل الورشة.",listOf("مكان الخدمة: منزل أو ورشة","نوع الشاشة: LED / LCD / OLED / سمارت","المقاس","الماركة","نوع العطل","هل تم فتح الشاشة قبل كده؟","صورة أو فيديو للعطل (إجباري)")))
@Composable fun HamzawySatRoot(){var selected by remember{mutableStateOf<Service?>(null)};var step by remember{mutableIntStateOf(0)};var answers by remember{mutableStateOf(mapOf<Int,String>())};var tab by remember{mutableStateOf("home")}
 Scaffold(bottomBar={NavigationBar{listOf("home" to "الرئيسية","orders" to "طلباتي","workshop" to "الورشة","profile" to "حسابي").forEach{(key,label)->NavigationBarItem(selected=tab==key,onClick={tab=key;selected=null},icon={Text("•")},label={Text(label)})}}}){padding->
 LazyColumn(Modifier.fillMaxSize().background(Color(0xFFF7F7F7)).padding(padding).padding(horizontal=18.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
 item{Spacer(Modifier.height(18.dp));Text("HAMZAWY SAT",color=Color(0xFFFF6A00),fontWeight=FontWeight.Black,letterSpacing=2.sp);Text(selected?.title?:"أهلاً بيك في حمزاوي سات",fontSize=25.sp,fontWeight=FontWeight.Bold);Text("خدمتك في بيتك بأعلى جودة",color=Color.DarkGray)}
 if(selected==null&&tab=="home"){
 item{Card(colors=CardDefaults.cardColors(containerColor=Color(0xFF171717)),shape=RoundedCornerShape(22.dp)){Column(Modifier.fillMaxWidth().padding(20.dp)){Text("خدمتك في بيتك بأعلى جودة",color=Color.White,fontSize=21.sp,fontWeight=FontWeight.Bold);Text("خدمات فنية وصيانة في القاهرة الكبرى",color=Color.LightGray);Button(onClick={selected=services.first();step=0;answers=emptyMap()}){Text("اطلب خدمة دلوقتي")}}}}
 items(services){service->Card(onClick={selected=service;step=0;answers=emptyMap()},shape=RoundedCornerShape(18.dp)){Column(Modifier.fillMaxWidth().padding(18.dp)){Text(service.title,fontWeight=FontWeight.Bold,fontSize=19.sp);Text(service.description,color=Color.Gray,fontSize=13.sp);Text("اطلب الخدمة ←",color=Color(0xFFFF6A00))}}}
 }else if(selected!=null){item{LinearProgressIndicator(progress={(step+1f)/(selected!!.questions.size+1f)},modifier=Modifier.fillMaxWidth());Text("الخطوة ${step+1} من ${selected!!.questions.size}",color=Color.Gray);Text(selected!!.questions[step],fontSize=21.sp,fontWeight=FontWeight.Bold);var answer by remember(step,selected){mutableStateOf(answers[step].orEmpty())};OutlinedTextField(value=answer,onValueChange={answer=it},modifier=Modifier.fillMaxWidth(),label={Text("اكتب إجابتك هنا")},minLines=2);Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){if(step>0)OutlinedButton(onClick={step--}){Text("رجوع")};Button(onClick={answers=answers+(step to answer);if(step<selected!!.questions.lastIndex)step++ else{tab="orders";selected=null;step=0}},enabled=answer.isNotBlank()){Text(if(step==selected!!.questions.lastIndex)"تأكيد الطلب" else "التالي")}};if(selected!!.title=="صيانة الشاشات"&&selected!!.questions[step].contains("صورة"))Text("رفع المرفقات يتطلب إعداد التخزين السحابي.",color=Color(0xFFB45309),fontSize=12.sp)}}
 else{item{Text(when(tab){"orders"->"طلباتك هتظهر هنا بعد ربط حساب العميل.";"workshop"->"تابع تذكرة الورشة باستخدام رقم HS الموجود في إيصال الاستلام.";else->"ملف العميل والعناوين المحفوظة وسجل الأجهزة."},fontSize=17.sp);Text("الاستلام والتسليم من مقر الورشة فقط",color=Color.Gray)}}
 }
 }
}
