package com.ktopencage.model

import com.google.gson.annotations.SerializedName

/**
 * class for debug purposes to review the sent request
 */
class OpenCageRequestData {
  val abbrv: Boolean = false
  val add_request: Boolean = false
  val autocomplete: Boolean = false
  val format: String? = null
  val key: String? = null
  val language: String? = null
  val limit: Int = 0
  val min_confidence: Int = 0
  val no_annotations: Boolean = false
  val no_dedupe: Boolean = false
  val no_record: Boolean = false
  val only_nominatim: Boolean = false
  val pretty: Boolean = false
  val proximity: String? = null
  val query: String? = null
  val version: String? = null
  val subkey: String? = null
  val bounds: String? = null
  val countrycode: String? = null
}