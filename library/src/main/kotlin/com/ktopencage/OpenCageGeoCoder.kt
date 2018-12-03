package com.ktopencage

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ktopencage.model.OpenCageRequest
import com.ktopencage.model.OpenCageResponse
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OpenCageGeoCoder(val apiKey: String) {
  private val gson: Gson
  private val okHttpClient: OkHttpClient = OkHttpClient()

  init {
    val gsonBuilder = GsonBuilder()
    val serializer = BooleanSerializer()
    gsonBuilder.registerTypeAdapter(Boolean::class.java, serializer)
    gsonBuilder.registerTypeAdapter(Boolean::class.javaPrimitiveType, serializer)
    gson = gsonBuilder.create()
  }

  /**
   * returns an observable for the geocoding request
   */
  fun requestObservable(request: OpenCageRequest): Observable<OpenCageResponse> {
    return Observable.create { emitter ->
      val response = handleRequst(request)
      emitter.onNext(response)
      emitter.onComplete()
    }
  }

  private fun genenrateUrl(openCageRequest: OpenCageRequest): HttpUrl {
    val urlBuilder = HttpUrl.Builder().scheme("https")
        .host("api.opencagedata.com")
        .addPathSegment("geocode")
        .addPathSegment("v1")
        .addPathSegment("json")
        .addQueryParameter("key", apiKey)

    val params = openCageRequest.generateRequestParams()
    val it = params.entries.iterator()

    while (it.hasNext()) {
      val pair = it.next()
      urlBuilder.addQueryParameter(pair.key, pair.value)
    }

    return urlBuilder.build()
  }

  /**
   * handle a geocoding request, can handle both forward and reverse requests.
   */
  @Throws(ResponseException::class, IOException::class)
  public fun handleRequst(openCageRequest: OpenCageRequest): OpenCageResponse {
    val url = genenrateUrl(openCageRequest)
    val requestBuilder = Request.Builder()

    requestBuilder.url(url)
    requestBuilder.header("User-Agent", "kotlin-sdk-agent")

    try {
      val response = okHttpClient.newCall(requestBuilder.build()).execute()

      if (response.code() != 200) {
        throw ResponseException(response.code(), "HttpException")
      }

      if (response.body() != null) {
        val body = response.body()!!.string()
        val openCageResponse = gson.fromJson(body, OpenCageResponse::class.java)
        val openCageResponseCode = openCageResponse.status!!.code

        return if (openCageResponseCode == 200) {
          openCageResponse
        } else {
          when (openCageResponseCode) {
            400 -> throw ResponseException(openCageResponseCode,
                "Invalid request (bad request; a required parameter is missing; invalid coordinates)")
            402 -> {
              throw ResponseException(openCageResponseCode, "Valid request but quota exceeded (payment required)")
            }
            403 -> {
              throw ResponseException(openCageResponseCode, "Invalid or missing api key (forbidden)")
            }
            404 -> throw ResponseException(openCageResponseCode, "Invalid API endpoint")
            405 -> throw ResponseException(openCageResponseCode, "Method not allowed (non-GET request)")
            408 -> throw ResponseException(openCageResponseCode, "Timeout; you can try again")
            410 -> throw ResponseException(openCageResponseCode, "Request too long")
            429 -> throw ResponseException(openCageResponseCode, "Too many requests (too quickly, rate limiting)")
            503 -> throw ResponseException(openCageResponseCode, "Internal server error")
            else -> throw ResponseException(openCageResponseCode, openCageResponse.status!!.message!!)
          }
        }
      } else {
        throw ResponseException(-1, "Empty response body")
      }
    } catch (e: IOException) {
      throw e
    }
  }
}
