package com.ktopencage.model

class OpenCageResult {
  var annotations: OpenCageAnnotations? = null
  var confidence: Int = 0
  var formatted: String? = null
  var geometry: OpenCageLatLng? = null
  var bounds: OpenCageBounds? = null
  var components: OpenCageComponents? = null
}
