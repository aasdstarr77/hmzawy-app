package com.hamzawysat.app.ui

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class Service(val title: String, val description: String, val questions: List<String>)
private data class LocalOrder(val id: String, val service: String, val createdAt: String, val answers: Map<String, String>)

private val services = listOf(
    Service("الدش والرسيفرات", "تركيب وضبط وصيانة الأطباق والرسيفرات وحل مشاكل الإشارة والتقطيع.", listOf("نوع الطلب: تركيب / ضبط إشارة / برمجة / صيانة", "حجم الطبق: 60 / 70 / 100 / مش عارف", "نوع الرسيفر: عادي / HD / 4K / عندي", "الدور ومكان السطح", "هل السلك واصل؟")),
    Service("كاميرات المراقبة", "تركيب وصيانة أنظمة IP وAnalog وربط المشاهدة بالموبايل.", listOf("نوع الطلب: نظام جديد / إضافة كاميرات / صيانة", "عدد الكاميرات المطلوب", "نوع المكان: شقة / محل / مخزن / فيلا", "نوع النظام: IP / Analog / مش عارف", "الكاميرات داخلية أم خارجية؟", "أيام التسجيل المطلوبة", "هل يوجد إنترنت بالمكان؟", "مساحة التخزين المطلوبة")),
    Service("صيانة الشاشات", "صيانة شاشات LED وLCD وOLED في المنزل أو داخل الورشة.", listOf("مكان الخدمة: منزل / سأحضر الشاشة للورشة", "نوع الشاشة: LED / LCD / OLED / سمارت", "مقاس الشاشة بالبوصة", "ماركة الشاشة والموديل", "نوع العطل: صوت بدون صورة / لا تعمل / كسر / تفصل / لمبة حمراء / عطل آخر", "هل يوجد صوت عند التشغيل؟", "هل توجد إضاءة خلفية؟", "هل تم فتح الشاشة قبل كده؟", "وصف إضافي للعطل (اكتب لا يوجد لو مفيش)"))
)

private fun loadOrders(context: Context): List<LocalOrder> = runCatching {
    val array = JSONArray(context.getSharedPreferences("local_orders", Context.MODE_PRIVATE).getString("items", "[]"))
    (0 until array.length()).map { i ->
        val item = array.getJSONObject(i)
        val obj = item.getJSONObject("answers")
        val answers = obj.keys().asSequence().associateWith { key -> obj.optString(key) }
        LocalOrder(item.getString("id"), item.getString("service"), item.getString("createdAt"), answers)
    }.reversed()
}.getOrDefault(emptyList())

private fun saveOrder(context: Context, order: LocalOrder) {
    val prefs = context.getSharedPreferences("local_orders", Context.MODE_PRIVATE)
    val array = JSONArray(prefs.getString("items", "[]"))
    val item = JSONObject().put("id", order.id).put("service", order.service).put("createdAt", order.createdAt)
    val answers = JSONObject()
    order.answers.forEach { (key, value) -> answers.put(key, value) }
    item.put("answers", answers)
    array.put(item)
    prefs.edit().putString("items", array.toString()).apply()
}

@Composable
fun HamzawySatRoot() {
    val context = LocalContext.current
    var selected by remember { mutableStateOf<Service?>(null) }
    var step by remember { mutableIntStateOf(0) }
    var answers by remember { mutableStateOf(mapOf<Int, String>()) }
    var tab by remember { mutableStateOf("home") }
    var orders by remember { mutableStateOf(loadOrders(context)) }
    var submittedId by remember { mutableStateOf<String?>(null) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf("home" to "الرئيسية", "orders" to "طلباتي", "workshop" to "الورشة", "profile" to "حسابي").forEach { (key, label) ->
                    NavigationBarItem(selected = tab == key, onClick = { tab = key; selected = null; submittedId = null }, icon = { Text("•") }, label = { Text(label) })
                }
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.fillMaxSize().background(Color(0xFFF7F7F7)).padding(padding).padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(Modifier.height(18.dp))
                Text("HAMZAWY SAT", color = Color(0xFFFF6A00), fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                Text(selected?.title ?: "أهلاً بيك في حمزاوي سات", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text("خدمتك في بيتك بأعلى جودة", color = Color.DarkGray)
            }
            if (submittedId != null) {
                item {
                    Card(shape = RoundedCornerShape(18.dp)) {
                        Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("تم حفظ طلبك على الجهاز", fontSize = 21.sp, fontWeight = FontWeight.Bold)
                            Text("رقم الطلب المحلي: $submittedId", fontWeight = FontWeight.SemiBold)
                            Text("الطلب محفوظ محلياً على هذا الهاتف، ولم يتم إرساله للمحل بعد لعدم تفعيل خادم التشغيل.")
                            Text("رقم الطلب المحلي ليس رقم تذكرة ورشة HS.", color = Color(0xFFB45309))
                            Button(onClick = { submittedId = null; tab = "orders" }) { Text("عرض طلباتي") }
                        }
                    }
                }
            } else if (selected == null && tab == "home") {
                item {
                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF171717)), shape = RoundedCornerShape(22.dp)) {
                        Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("خدمتك في بيتك بأعلى جودة", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                            Text("خدمات فنية وصيانة في القاهرة الكبرى", color = Color.LightGray)
                            Button(onClick = { selected = services.first(); step = 0; answers = emptyMap() }) { Text("اطلب خدمة دلوقتي") }
                        }
                    }
                }
                items(services) { service ->
                    Card(onClick = { selected = service; step = 0; answers = emptyMap() }, shape = RoundedCornerShape(18.dp)) {
                        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(service.title, fontWeight = FontWeight.Bold, fontSize = 19.sp)
                            Text(service.description, color = Color.Gray, fontSize = 13.sp)
                            Text("اطلب الخدمة ←", color = Color(0xFFFF6A00))
                        }
                    }
                }
                item { Text("الخدمة الميدانية داخل القاهرة الكبرى، أو تسليم واستلام الجهاز من مقر الورشة فقط. لا يوجد استلام أو توصيل دليفري.", color = Color.Gray, fontSize = 12.sp) }
            } else if (selected != null) {
                val service = selected!!
                item {
                    LinearProgressIndicator(progress = { (step + 1f) / service.questions.size }, modifier = Modifier.fillMaxWidth())
                    Text("الخطوة ${step + 1} من ${service.questions.size}", color = Color.Gray)
                    Text(service.questions[step], fontSize = 21.sp, fontWeight = FontWeight.Bold)
                    var answer by remember(step, service) { mutableStateOf(answers[step].orEmpty()) }
                    OutlinedTextField(value = answer, onValueChange = { answer = it }, modifier = Modifier.fillMaxWidth(), label = { Text("اكتب إجابتك هنا") }, minLines = 2)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (step > 0) OutlinedButton(onClick = { step-- }) { Text("رجوع") }
                        Button(
                            onClick = {
                                val updated = answers + (step to answer.trim())
                                answers = updated
                                if (step < service.questions.lastIndex) step++ else {
                                    val id = "LCL-" + UUID.randomUUID().toString().take(8).uppercase()
                                    val order = LocalOrder(id, service.title, java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale("ar")).format(java.util.Date()), updated.mapKeys { service.questions[it.key] })
                                    saveOrder(context, order)
                                    orders = loadOrders(context)
                                    submittedId = id
                                    selected = null
                                    step = 0
                                }
                            },
                            enabled = answer.isNotBlank()
                        ) { Text(if (step == service.questions.lastIndex) "حفظ الطلب" else "التالي") }
                    }
                }
            } else if (tab == "orders") {
                if (orders.isEmpty()) item { Text("لسه مفيش طلبات محفوظة على الجهاز.", fontSize = 17.sp) }
                items(orders, key = { it.id }) { order ->
                    Card(shape = RoundedCornerShape(16.dp)) {
                        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(order.service, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(order.id, color = Color(0xFFFF6A00), fontWeight = FontWeight.SemiBold)
                            Text(order.createdAt, color = Color.Gray, fontSize = 12.sp)
                            Text("محفوظ على الهاتف - لم يُرسل للمحل", color = Color(0xFFB45309), fontSize = 12.sp)
                            order.answers.forEach { (question, answer) -> Text("$question\n$answer", fontSize = 13.sp) }
                        }
                    }
                }
            } else {
                item {
                    Text(
                        when (tab) {
                            "workshop" -> "تذاكر الورشة الفعلية تصدر من المحل عند استلام الجهاز. تابع برقم HS الموجود في إيصال الاستلام."
                            else -> "ملف العميل والعناوين المحفوظة وسجل الأجهزة ستتاح بعد تفعيل تسجيل الدخول والخادم."
                        },
                        fontSize = 17.sp
                    )
                    Text("الاستلام والتسليم من مقر الورشة فقط. لا يوجد توصيل للأجهزة.", color = Color.Gray)
                }
            }
        }
    }
}
