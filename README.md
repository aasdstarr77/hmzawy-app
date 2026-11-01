# حمزاوي سات | Hamzawy Sat

نظام رقمي لخدمات الدش والرسيفرات وكاميرات المراقبة وصيانة الشاشات.

**الشعار:** خدمتك في بيتك بأعلى جودة.

## قواعد التشغيل
- نطاق الخدمة القاهرة الكبرى.
- لا يوجد استلام أو توصيل أو شحن للأجهزة؛ العميل يحضر الجهاز للورشة ويستلمه بنفسه.
- رقم الورشة HS-YYYY-NNN يولد من الخادم بعداد ذري سنوي وفهرس فريد.
- حالات الورشة ست حالات ثابتة موثقة في docs/WORKFLOW.md.
- لا تضع أسرارًا أو ملفات keystore في المستودع.

## بناء Android
يتطلب Android Studio أو Android SDK 35 وJDK 17 وGradle 8.9. نفّذ: gradle :app:assembleDebug

## تشغيل Backend
من مجلد backend: انسخ .env.example إلى .env، ثم اضبط بيانات MongoDB وJWT_SECRET، ونفّذ npm install ثم npm run dev. MongoDB transactions تتطلب Replica Set.

## إعداد التكاملات
راجع docs/SETUP.md. OTP وFCM وWhatsApp وMaps وتخزين الصور تحتاج بيانات اعتماد من مزوديها قبل تشغيلها في الإنتاج. نقاط OTP غير مفعلة افتراضيًا ولا تحاكي تحققًا أمنيًا.

## بناء APK آليًا
كل push على main يشغل GitHub Actions لبناء Debug APK ورفعه كـ Artifact باسم hamzawy-sat-debug-apk. نجاح رفع الكود لا يعني نجاح البناء؛ راجع حالة Workflow قبل توزيع التطبيق.
