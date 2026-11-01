# REST API

- GET /health: فحص الخدمة.
- POST /api/auth/otp/request وPOST /api/auth/otp/verify: غير مفعّلين قبل إعداد مزود OTP.
- POST /api/orders: إنشاء طلب لمستخدم مسجل.
- GET /api/orders: طلبات العميل أو كل الطلبات للأدمن.
- GET /api/orders/:id: تفاصيل الطلب.
- PATCH /api/orders/:id: تحديث إداري.
- GET /api/workshop/receipt/:receipt: تتبع عام محدود.
- GET /api/workshop: قائمة الورشة للأدمن.
- POST /api/workshop: إنشاء تذكرة للأدمن مع رقم يولده الخادم.
- PATCH /api/workshop/:id/status: تغيير حالة الورشة.

Authorization: Bearer JWT. جميع المبالغ بالقروش الصحيحة.
