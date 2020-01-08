package com.ktopencage.model

import com.google.gson.annotations.SerializedName
import com.ktopencage.model.OpenCageDMS

class OpenCageAnnotations {
  var DMS: OpenCageDMS? = null
  var MGRS: String? = null
  var OSM: OpenCageOSM? = null
  var geohash: String? = null
  var flag: String? = null
  var sun: OpenCageSun? = null
  var wikidata: String? = null
  var qibla: Double? = null
  var what3words: OpenCageWhat3Words? = null
  var currency: OpenCageCurrency? = null
  @SerializedName("Maidenhead")
  var maidenhead: String? = null
  @SerializedName("Mercator")
  var mercator: OpenCageMercator? = null
  @SerializedName("timezone")
  var timeZone: OpenCageTimeZone? = null
  @SerializedName("ITM")
  var ITM: OpenCageITM? = null
  @SerializedName("OSGB")
  var OSGB: OpenCageOSGB? = null
  @SerializedName("callingcode")
  var phoneCountryCode: Int = 0
}
