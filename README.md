# ktopencage

[![CircleCI](https://circleci.com/gh/sgimp/ktopencage.svg?style=svg)](https://circleci.com/gh/sgimp/ktopencage)

OpenCage geocoding client for Android.

### How to use:

You will need to sign up for an API key on [the OpenCage site](https://opencagedata.com).
See the full [OpenCage Geocoding API documentation](https://opencagedata.com/api)

### Gradle

Repository

    ```
    repositories {
      maven { url 'https://jitpack.io' }
      maven {
        url "https://dl.bintray.com/andevengimpel/maven/"
      }
    }
    ```

Dependency

    ```
    dependencies {
          implementation 'andev:ktopencage:library_version'
    }
    ```

### Code samples

    ```
    var geoCoder = OpenCageGeoCoder(API_KEY)

    //forward request

    val request = OpenCageRequest("Paris")
    request.minConfidence = 1

    //Call the handleRequest method in the background

    try {
       val response = geoCoder.handleRequst(request)
       if (!response.results.isNullOrEmpty()) {
          val result = response.results!![0]
          Log.d(
              TAG,
              result.geometry!!.lat.toString() + "," + result.geometry!!.lng.toString())
       }
    } catch (e: ResponseException) {
       e.printStackTrace()
    }

    //reverse request
    val request = OpenCageRequest(40.698613, -73.972494)

    //Call the handleRequest method in the background

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
    ```

### Libraries

okhttp - https://square.github.io/okhttp/

Gson - https://github.com/google/gson

kotlin-stdlib-jdk7 - https://kotlinlang.org/


License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
