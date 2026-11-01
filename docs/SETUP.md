# إعداد المشروع

Android: افتح المشروع في Android Studio باستخدام JDK 17 وAndroid SDK 35، واضبط BASE_URL في app/build.gradle.kts، ثم نفّذ ./gradlew :app:assembleDebug.

Backend: استخدم Node.js 20+. داخل backend انسخ .env.example إلى .env واضبط MONGODB_URI وJWT_SECRET ثم npm install وnpm run dev. معاملات MongoDB تتطلب Replica Set؛ استخدم Atlas أو Replica Set.

OTP وWhatsApp وFCM وMaps وتخزين الصور تحتاج بيانات اعتماد آمنة قبل التشغيل. نقاط OTP ترجع 501 حتى ربط مزود فعلي؛ لا يوجد تحقق وهمي. لا ترفع مفاتيح أو keystore إلى Git.
