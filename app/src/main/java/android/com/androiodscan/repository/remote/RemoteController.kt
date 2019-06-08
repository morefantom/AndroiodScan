package android.com.androiodscan.repository.remote

import android.com.androiodscan.repository.remote.dao.ApiResponseDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteController {

    const val BASE_URL = "https://mp-android-challenge.herokuapp.com"

    private fun <T> builder(endpoint: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(endpoint)
    }

    fun apiResponseDao(): ApiResponseDao{
        return builder(ApiResponseDao::class.java)
    }

}