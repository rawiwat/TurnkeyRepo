package com.example.turnkeyapp

import android.content.Context
import android.widget.Toast
import com.example.turnkeyapp.ApiClient.apiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.security.interfaces.ECPublicKey

fun createPrivateKeyPostRequest(jsonRequest: String, context:Context) {
    val requestBody = jsonRequest.toRequestBody("application/json".toMediaType())
    /*val publicKeyString = "027832910f9b1dabb053384fa08fa79b4a69fe8dd98b50808e0811fd126238183c"
    val privateKeyString = "5c44170af43301f3b28d7ee3bd2e164b090bd6e3425463bf5d01cdc3c752cf40"*/
    //val privateKeyString = "f2184e7eaca9a8e07ca14f90af55e3c4a3641875114c3ea298b39596b6ef40f7"
    //val publicKeyString = "039e72d35ab67d01949d5326cdf949247d98ba3f0546130e7a8ae7c873cd1db8cb"
    //val encodedJsonStamp = generateXStamp(jsonRequest, publicKeyString, privateKeyString)
    val newKeyPair = newTkApiKey()
    val encodedJsonStamp = generateStamp(
        jsonRequest,
        newKeyPair)
    apiService.postData(encodedJsonStamp,requestBody)
        .enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    println(response.body())
                    Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG).show()
                } else {
                    println(response)
                    Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
            }
        }
    )
}

object ApiClient {
    private const val BASE_URL = "https://api.turnkey.com"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
@POST("/public/v1/submit/create_private_keys")
    fun postData(
    @Header("X-Stamp") headerValue: String,
    @Body requestBody: RequestBody
    ): Call<ResponseBody>
}

