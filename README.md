# حمزاوي سات | Hamzawy Sat

نظام إدارة خدمات الدش والرسيفرات وكاميرات المراقبة وصيانة الشاشات.

> خدمتك في بيتك بأعلى جودة.

هذا المستودع هو نقطة البداية الرسمية للمشروع. يتضمن تطبيق Android أصليًا بـ Kotlin وJetpack Compose، وخلفية Node.js/Express، وتوثيق API، وإعداد بناء APK آليًا عبر GitHub Actions.

## قواعد تشغيل أساسية
- نطاق الخدمة: القاهرة الكبرى.
- لا توجد خدمة استلام أو توصيل أو شحن للأجهزة؛ عميل الورشة يحضر الجهاز ويستلمه بنفسه.
- رقم الورشة بصيغة `HS-YYYY-NNN` يولّد من الخادم باستخدام عدّاد ذري وفهرس فريد.
- حالات الورشة ست حالات ثابتة كما هو موثق في `docs/WORKFLOW.md`.
- لا تضع مفاتيح أو كلمات مرور أو ملفات keystore في Git.

## المتطلبات
- Android Studio حديث وJDK 17 وAndroid SDK 35.
- Node.js 20+ وMongoDB.

## بناء Android
```bash
./gradlew :app:assembleDebug
```
الناتج: `app/build/outputs/apk/debug/app-debug.apk`.

## تشغيل Backend
```bash
cd backend
cp .env.example .env
npm install
npm run dev
```

## الإعدادات
راجع `docs/SETUP.md` و`backend/.env.example`. عنوان API الافتراضي للتطوير قابل للتغيير من `local.properties` أو متغيرات Gradle، ولا تُضمّن الأسرار في المستودع.

## الحالة
المستودع في مرحلة التأسيس والبناء. البناء الآلي يتحقق من تجميع APK عند كل push إلى `main`، ولا يعني ذلك أن خدمات الإنتاج الخارجية (MongoDB/FCM/WhatsApp/Maps) مهيأة دون بيانات اعتمادها.