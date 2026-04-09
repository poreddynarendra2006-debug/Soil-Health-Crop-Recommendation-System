# 🌾 Crop AI — Soil Health Analysis and Crop Recommendation System

## 📌 Overview

Crop AI is a full-stack machine learning-based system designed to recommend the most suitable crop based on soil and climate parameters.
It combines an Android mobile application, a FastAPI backend, and a trained Random Forest model to deliver accurate predictions in real-time.

---

## ❗ Problem Statement

Farmers face several challenges while selecting crops:

* ❌ Wrong crop selection leads to poor yield
* 🌱 Lack of awareness about soil nutrients (N, P, K, pH)
* 🌦️ Ignoring climate conditions causes crop failure
* 💸 Economic losses due to incorrect decisions

---

## 🎯 Project Objective

* Collect soil and climate data (N, P, K, pH, temperature, humidity, rainfall)
* Use Machine Learning to predict the best crop
* Deliver results through a mobile application
* Assist farmers with crop recommendations and insights

---

## 🏗️ System Architecture

### 📱 Android App

* Kotlin + MVVM Architecture
* Retrofit & OkHttp for API calls
* Material Design 3 UI
* Input form and result display

### ⚡ FastAPI Backend

* Python FastAPI REST API
* Pydantic models for validation
* Endpoint: `/predict`
* JSON response handling

### 🤖 Machine Learning Model

* Algorithm: Random Forest Classifier
* Library: scikit-learn
* 22 crop classes
* Model stored using Pickle

---

## 🛠️ Technology Stack

| Layer        | Technology              | Purpose           |
| ------------ | ----------------------- | ----------------- |
| Mobile App   | Kotlin + Android Studio | App development   |
| Architecture | MVVM + LiveData         | Clean structure   |
| Networking   | Retrofit + OkHttp       | API communication |
| Backend      | FastAPI (Python)        | Server            |
| ML Model     | scikit-learn            | Prediction        |
| Storage      | Pickle (.pkl)           | Model storage     |
| Deployment   | ngrok                   | Public API access |

---

## 🤖 Machine Learning Details

* **Algorithm:** Random Forest Classifier
* **Dataset:** Crop Recommendation Dataset
* **Training Split:** 80% Train / 20% Test
* **Accuracy:** ~99%
* **Output:** 22 Crop Types

### 📥 Input Features (7)

* Nitrogen (N)
* Phosphorus (P)
* Potassium (K)
* Temperature
* Humidity
* pH Value
* Rainfall

---

## 📱 App Features

* 🌱 Splash screen with animation
* 📝 Smart input form with validation
* 🔍 AI-based crop prediction
* 📊 Result dashboard with crop details
* 💡 Growing tips for farmers
* 📤 Share results via apps

---

## 📊 Results

* ✅ Supports **22 crops**
* 📈 Achieved **~99% accuracy**
* ⚡ API response time < 2 seconds

---

## ⚠️ Challenges & Solutions

| Challenge         | Solution                 |
| ----------------- | ------------------------ |
| Network issues    | Used ngrok for tunneling |
| API mismatch      | Used JSON with Pydantic  |
| Emulator slow     | Used real device         |
| UI issues         | Fixed text visibility    |
| Model limitations | Documented edge cases    |

---

## 🚀 Future Enhancements

* Cloud deployment (AWS / Render)
* Soil image analysis using camera
* Multi-language support
* Offline prediction using on-device ML

---

## 👨‍💻 Team Members

* Sree Sai Surya Vinay
* G.V.R. Shanmukha Sai
* P. Narendra Reddy
* A. Charukesh

---
## 📌 Conclusion

This project demonstrates how machine learning can be integrated with mobile and backend systems to provide intelligent crop recommendations.
It helps farmers make data-driven decisions and improve agricultural productivity.

⭐ If you like this project, consider giving it a star!
