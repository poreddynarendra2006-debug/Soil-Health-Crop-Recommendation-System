package com.cropapp.recommendation

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CropApiService {

    @POST("predict")
    suspend fun predictCrop(
        @Body input: CropInputData
    ): Response<CropPredictionResponse>
}