package com.cropapp.recommendation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CropRepository {

    private val api = RetrofitClient.instance

    suspend fun getCropRecommendation(
        n: Int, p: Int, k: Int,
        temperature: Double, humidity: Double,
        ph: Double, rainfall: Double
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val input = CropInputData(
                    N = n, P = p, K = k,
                    temperature = temperature,
                    humidity = humidity,
                    ph = ph,
                    rainfall = rainfall
                )
                val response = api.predictCrop(input)
                if (response.isSuccessful) {
                    val crop = response.body()?.recommendedCrop
                    if (crop != null) {
                        Result.success(crop)
                    } else {
                        Result.failure(Exception("Empty response from server"))
                    }
                } else {
                    Result.failure(Exception("Server error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}