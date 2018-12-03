package andev.com.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ktopencage.OpenCageGeoCoder
import com.ktopencage.model.OpenCageRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
  val API_KEY = ""

  private val TAG = "KtOpencage"
  private lateinit var geoCoder: OpenCageGeoCoder
  private var disposable = Disposables.empty()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    geoCoder = OpenCageGeoCoder(API_KEY)
  }

  private fun executeForwardRequest() {
    disposable.dispose()

    val request = OpenCageRequest("Paris")
    request.minConfidence = 1

    disposable = geoCoder.requestObservable(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (!it.results.isNullOrEmpty()) {
            val result = it.results!![0]
            Log.d(TAG, result.geometry!!.lat.toString() + "," + result.geometry!!.lng.toString())
          }
        }, { throwable -> throwable.printStackTrace() })
  }

  private fun executeReverseRequest() {
    disposable.dispose()

    //create the proper request
    val request = OpenCageRequest(40.698613, -73.972494)

    //call either the reverse method from a background thread.
    //Or create an observable, make sure the observable will be executed on a background thread.
    disposable = geoCoder.requestObservable(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (!it.results.isNullOrEmpty()) {
            val result = it.results!![0]
            Log.d(TAG,result.formatted)
          }
        }, { throwable ->
          throwable.printStackTrace()
        })
  }

}
