package com.cropapp.recommendation

import com.google.gson.annotations.SerializedName

data class CropPredictionResponse(
    @SerializedName("recommended_crop")
    val recommendedCrop: String
)

data class CropInputData(
    @SerializedName("N") val N: Int,
    @SerializedName("P") val P: Int,
    @SerializedName("K") val K: Int,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("ph") val ph: Double,
    @SerializedName("rainfall") val rainfall: Double
)