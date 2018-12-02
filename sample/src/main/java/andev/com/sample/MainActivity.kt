package andev.com.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import com.ktopencage.OpenCageGeoCoder
import com.ktopencage.model.OpenCageRequest

class MainActivity : AppCompatActivity() {
  val API_KEY = ""

  private lateinit var geoCoder: OpenCageGeoCoder
  private var disposable = Disposables.empty()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    geoCoder = OpenCageGeoCoder(API_KEY)

    executeForwardRequest();
  }

  private fun executeForwardRequest() {
    disposable.dispose()

    val request = OpenCageRequest("Paris")
    request.minConfidence = 1

    disposable = geoCoder.forwardRequestObservable(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ }, { throwable -> throwable.printStackTrace() })
  }

  private fun executeReverseRequest() {
    disposable.dispose()

    //create the proper request
    val request = OpenCageRequest(40.698613, -73.972494)

    //call either the reverse method from a background thread.
    //Or create an observable, make sure the observable will be executed on a background thread.
    disposable = geoCoder.reverseRequestObservable(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          //access the response object
        }, { throwable ->
          throwable.printStackTrace()
        })
  }

}
