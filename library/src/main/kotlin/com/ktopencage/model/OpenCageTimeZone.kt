package com.ktopencage.model

import com.google.gson.annotations.SerializedName

class OpenCageTimeZone {
  var name: String? = null
  @SerializedName("now_in_dst")
  var nowInDst: Int = 0
  @SerializedName("offset_sec")
  var offsetSec: Int = 0
  @SerializedName("offset_string")
  var offsetString: Int = 0
  @SerializedName("short_name")
  var shortName: String? = null
}
