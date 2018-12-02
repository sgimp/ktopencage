package com.ktopencage.model

import com.google.gson.annotations.SerializedName

class OpenCageComponents {
  var city: String? = null
  var clothes: String? = null
  var country: String? = null
  var road: String? = null
  var state: String? = null
  var suburb: String? = null
  var town: String? = null
  var postcode: String? = null
  var county: String? = null

  @SerializedName("ISO_3166-1_alpha-2")
  var iso31661: String? = null

  @SerializedName("_type")
  var type: String? = null

  @SerializedName("city_district")
  var cityDistrict: String? = null

  @SerializedName("country_code")
  var countryCode: String? = null

  @SerializedName("house_number")
  var houseNumber: String? = null

  @SerializedName("political_union")
  var politicalUnion: String? = null
}
