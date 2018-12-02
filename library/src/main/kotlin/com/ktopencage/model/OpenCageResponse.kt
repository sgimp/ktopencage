package com.ktopencage.model

import com.google.gson.annotations.SerializedName

class OpenCageResponse {
  var documentation: String? = null
  var licenses: List<OpenCageLicense>? = null
  var rate: OpenCageRate? = null
  var results: List<OpenCageResult>? = null
  var status: OpenCageStatus? = null
  var thanks: String? = null
  var timestamp: OpenCageTimestamp? = null
  var request: OpenCageRequest? = null
  @SerializedName("stay_informed")
  var stayInformed: OpenCageStayInformed? = null
  @SerializedName("total_results")
  private val totalResults: Int = 0
}
