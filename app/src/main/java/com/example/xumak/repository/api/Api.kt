package com.example.xumak.repository.api

import android.util.Log
import com.example.xumak.repository.models.CharacterItem
import com.example.xumak.repository.models.ResponseRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit


class Api {
    private val URL_PROTOCOLO = "https"
    private val URL_BASE_DOMINIO = "breakingbadapi.com"
    private val URL_BASE = "${URL_PROTOCOLO}://${URL_BASE_DOMINIO}/api/"


    fun getRetrofit(): ApiInterface {
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    suspend inline fun <reified T> GET(url: String, java: Class<T>) = withContext(Dispatchers.Main) {
        return@withContext try {
            val typeToken = TypeToken.getParameterized(ArrayList::class.java, java).type
            val result: ResponseRequest<ArrayList<T>?> = validResponse(getRetrofit().get(url), typeToken)
            return@withContext result
        } catch (e: UnknownHostException) {
            ResponseRequest(false, null, "Revise su conexión a internet")
        } catch (e: Exception) {
            Log.e("E", e.message.toString())
            ResponseRequest(false, null, "La petición ha fallado")
        }
    }

    fun <T> validResponse(call: Response<String>, tClass: Type): ResponseRequest<ArrayList<T>?> {
        val item = call.body()
        return if (call.isSuccessful) {
            val data: ArrayList<T> = Gson().fromJson(item.toString(), tClass)
            return ResponseRequest(true, data, "Exito")
        } else {
            ResponseRequest(false, null, "Error")
        }
    }

}