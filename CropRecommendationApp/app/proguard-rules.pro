# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep Retrofit models
-keep class com.cropapp.recommendation.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# Gson
-keep class com.google.gson.** { *; }
-keepattributes SerializedName
