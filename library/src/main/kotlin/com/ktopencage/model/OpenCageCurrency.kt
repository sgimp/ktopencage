package com.ktopencage.model

import com.google.gson.annotations.SerializedName

class OpenCageCurrency {
  @SerializedName("alternate_symbols")
  var alternateSymbols: List<String>? = null
  @SerializedName("decimal_mark")
  var decimalMark: String? = null
  @SerializedName("html_entity")
  var htmlEntity: String? = null
  @SerializedName("iso_code")
  var isoCode: String? = null
  @SerializedName("iso_numeric")
  var isoNumeric: Int = 0
  var name: String? = null
  @SerializedName("smallest_denomination")
  var smallestDenomination: Int = 0
  var subunit: String? = null
  @SerializedName("subunit_to_unit")
  var subunitToUnit: Int = 0
  var symbol: String? = null
  @SerializedName("symbol_first")
  var symbolFirst: Boolean = false
  @SerializedName("thousands_separator")
  var thousandsSeparator: String? = null
}
