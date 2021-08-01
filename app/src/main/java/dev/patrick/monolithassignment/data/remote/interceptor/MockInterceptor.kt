package dev.patrick.monolithassignment.data.remote.interceptor

import android.content.Context
import dev.patrick.monolithassignment.utils.readFileFromAssets
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val filename = request.url.pathSegments.last()
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("")
            .code(200)
            .body(context.readFileFromAssets("$filename.json").toResponseBody(JSON_TYPE))
            .build()
    }

    companion object {
        private val JSON_TYPE = "application/json".toMediaTypeOrNull()
        private const val MOCK = "mock"
    }
}
