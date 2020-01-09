package andev.com.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.ktopencage.OpenCageGeoCoder
import com.ktopencage.ResponseException
import com.ktopencage.model.OpenCageRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : Activity(), CoroutineScope {
  private lateinit var job: Job
  override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

  private val TAG = "KtOpencage"
  val API_KEY = ""
  private lateinit var geoCoder: OpenCageGeoCoder

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    job = Job()
    geoCoder = OpenCageGeoCoder(API_KEY)

    launch(Dispatchers.IO) {
      executeReverseRequest()

      executeForwardRequest()
    }
  }

  private fun executeReverseRequest() {
    //create the proper request
    val request = OpenCageRequest(40.698613, -73.972494)

    try {
      // call handle request from a background thread
      val response = geoCoder.handleRequest(request)
      if (!response.results.isNullOrEmpty()) {
        val result = response.results!![0]
        Log.d(TAG, result.formatted)
      }
    } catch (e: ResponseException) {
      e.printStackTrace()
    }
  }

  private fun executeForwardRequest() {

    val request = OpenCageRequest("Paris")
    request.minConfidence = 1

    try {
      val response = geoCoder.handleRequest(request)
      if (!response.results.isNullOrEmpty()) {
        val result = response.results!![0]
        Log.d(
            TAG,
            result.geometry!!.lat.toString() + "," + result.geometry!!.lng.toString()
        )
      }
    } catch (e: ResponseException) {
      e.printStackTrace()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    job.cancel()
  }
}
