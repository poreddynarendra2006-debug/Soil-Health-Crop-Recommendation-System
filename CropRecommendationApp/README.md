# 🌾 Crop AI - Android App

A full-featured Android application that connects to your FastAPI ML backend to recommend the best crop based on soil and climate parameters.

---

## 📁 Project Structure

```
CropRecommendationApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/cropapp/recommendation/
│   │   │   ├── SplashActivity.kt       ← Splash screen
│   │   │   ├── MainActivity.kt         ← Input form screen
│   │   │   ├── ResultActivity.kt       ← Result + tips screen
│   │   │   ├── CropViewModel.kt        ← ViewModel (MVVM)
│   │   │   ├── CropRepository.kt       ← Data layer
│   │   │   ├── CropApiService.kt       ← Retrofit API interface
│   │   │   ├── RetrofitClient.kt       ← HTTP client singleton
│   │   │   └── Models.kt               ← Data classes
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_splash.xml
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── activity_result.xml
│   │   │   ├── drawable/               ← Icons & logo
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## 🚀 Setup Instructions

### Step 1 — Open in Android Studio
1. Open Android Studio (Hedgehog or newer recommended)
2. Click **File → Open** and select the `CropRecommendationApp` folder
3. Wait for Gradle to sync (first sync downloads dependencies — ~2–3 min)

### Step 2 — Start your FastAPI Backend
```bash
# In your project folder with backend_main.py and crop_model.pkl:
pip install fastapi uvicorn pandas scikit-learn

uvicorn backend_main:app --reload --host 0.0.0.0 --port 8000
```

### Step 3 — Configure the API URL

Open `RetrofitClient.kt` and set the correct `BASE_URL`:

| Scenario | URL to use |
|---|---|
| Android Emulator → PC localhost | `http://10.0.2.2:8000/` ✅ (default) |
| Real Android device (same WiFi) | `http://YOUR_PC_LOCAL_IP:8000/` |
| Deployed cloud server | `https://your-server-domain.com/` |

To find your PC's local IP:
- **Windows**: Run `ipconfig` → look for IPv4 address
- **Mac/Linux**: Run `ifconfig` or `ip addr`

### Step 4 — Run the App
1. Connect your Android device (USB debugging ON) or start an emulator
2. Click the ▶️ **Run** button in Android Studio
3. The app will install and launch automatically

---

## 📱 App Features

- ✅ **Splash screen** with animated logo
- ✅ **Input form** with 7 soil/climate parameters
- ✅ **Real-time validation** with helpful error messages
- ✅ **Loading indicator** while API call is in progress
- ✅ **Result screen** with crop emoji, description, and growing tips
- ✅ **Share** your result via any app (WhatsApp, email, etc.)
- ✅ **22 crop types** supported with custom tips
- ✅ Material Design 3 UI with green agriculture theme
- ✅ MVVM architecture (ViewModel + LiveData + Repository)
- ✅ Kotlin Coroutines for async networking

---

## 🔧 Sample Input Values (for testing)

| Parameter | Sample Value |
|---|---|
| Nitrogen (N) | 90 |
| Phosphorus (P) | 42 |
| Potassium (K) | 43 |
| Temperature | 20.5 |
| Humidity | 82 |
| pH | 6.5 |
| Rainfall | 200 |

Expected output: **Rice** 🌾

---

## 🌿 Supported Crops

Rice, Maize, Chickpea, Kidney Beans, Pigeon Peas, Moth Beans,
Mung Bean, Black Gram, Lentil, Pomegranate, Banana, Mango,
Grapes, Watermelon, Musk Melon, Apple, Orange, Papaya,
Coconut, Cotton, Jute, Coffee

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Material Design 3, ViewBinding |
| Architecture | MVVM (ViewModel + LiveData) |
| Networking | Retrofit 2 + OkHttp |
| Async | Kotlin Coroutines |
| Backend | FastAPI + scikit-learn (Python) |
| ML Model | Random Forest Classifier |

---

## ❓ Troubleshooting

**"Connection refused" error:**
- Make sure your FastAPI server is running (`uvicorn backend_main:app --reload --host 0.0.0.0 --port 8000`)
- Check that you're using the right BASE_URL in `RetrofitClient.kt`
- On real devices, make sure phone and PC are on the same WiFi network

**Gradle sync fails:**
- Make sure you have internet access
- Try **File → Invalidate Caches / Restart**
- Update Android Studio to the latest version

**App crashes on launch:**
- Check Logcat in Android Studio for error details
- Minimum SDK is 24 (Android 7.0) — use a compatible device/emulator
