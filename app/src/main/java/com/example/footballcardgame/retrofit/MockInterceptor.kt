package com.example.footballcardgame.retrofit

import android.content.Context
import android.util.Log
import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.common.Utils
import okhttp3.*

class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith(Constants.PLAYER_DETAILS_ENDPOINT) -> {
                Utils.getJsonStringFromAsset(context, Constants.MOCK_JSON_FILE_NAME)
            }
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(Constants.SUCCESS_CODE)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}
