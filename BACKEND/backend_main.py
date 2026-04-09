from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import pandas as pd
import pickle
import os

# Initialize FastAPI app
app = FastAPI(title="Soil Health Crop Recommendation API")

# Allow all origins
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Get correct path for model file
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
model_path = os.path.join(BASE_DIR, "crop_model.pkl")

# Load trained ML model
model = pickle.load(open(model_path, "rb"))

# Request body model
class CropInput(BaseModel):
    N: int
    P: int
    K: int
    temperature: float
    humidity: float
    ph: float
    rainfall: float

# Root endpoint
@app.get("/")
def home():
    return {"message": "Crop Recommendation API is running"}

# Prediction endpoint - accepts JSON body
@app.post("/predict")
def predict_crop(data: CropInput):
    input_data = pd.DataFrame(
        [[data.N, data.P, data.K, data.temperature, data.humidity, data.ph, data.rainfall]],
        columns=["N", "P", "K", "temperature", "humidity", "ph", "rainfall"]
    )
    prediction = model.predict(input_data)
    return {
        "recommended_crop": prediction[0]
    }