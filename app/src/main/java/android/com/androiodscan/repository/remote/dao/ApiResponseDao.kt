package android.com.androiodscan.repository.remote.dao

import android.com.androiodscan.data.ApiResponse
import retrofit2.http.GET
import retrofit2.Call

interface ApiResponseDao {

    @GET("/data")
    fun getApiResponse(): Call<List<ApiResponse>>
}