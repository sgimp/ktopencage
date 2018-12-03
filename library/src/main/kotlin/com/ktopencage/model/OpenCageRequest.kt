package com.ktopencage.model

class OpenCageRequest {
  private var isReverseRequest: Boolean;

  var subkey: String? = null
  var language: String? = null
  var limit: Int? = null
  var minConfidence: Int? = null
  var noAnnotations: Boolean = false
  var noDedupe: Boolean = false
  var pretty: Boolean = false
  var abbrv: Boolean = false
  var noRecord: Boolean = false
  var onlyNominatim: Boolean = false
  var debugRequest: Boolean = false

  val bounds: OpenCageBounds? = null
  var restrictToCountryCode: String? = null

  private var queryParts = mutableListOf<String>()
  private val queryPartSeparator = ","

  private var lat: Double? = null
  private var lng: Double? = null

  /**
   * Reverse geocoding request constructor
   */
  constructor(lat: Double, lng: Double) {
    this.lat = lat
    this.lng = lng

    isReverseRequest = true
  }

  /**
   * Forward geocoding request constructor
   */
  constructor(vararg queryParts: String) {
    for (queryPart in queryParts) {
      this.queryParts.add(queryPart)
    }

    isReverseRequest = false
  }

  fun generateRequestParams(): MutableMap<String, String> {
    val requestParams = mutableMapOf<String, String>()

    if (subkey != null) {
      requestParams["subkey"] = subkey!!
    }

    if (language != null) {
      requestParams["language"] = language!!
    }

    if (limit != null) {
      requestParams["limit"] = limit.toString()
    }

    if (minConfidence != null) {
      requestParams["min_confidence"] = minConfidence.toString()
    }

    if (noAnnotations) {
      requestParams["no_annotations"] = "1"
    }

    if (noDedupe) {
      requestParams["no_dedupe"] = "1"
    }

    if (pretty) {
      requestParams["pretty"] = "1"
    }

    if (abbrv) {
      requestParams["abbrv"] = "1"
    }

    if (noRecord) {
      requestParams["no_record"] = "1"
    }

    if (onlyNominatim) {
      requestParams["only_nominatim"] = "1"
    }

    if (debugRequest) {
      requestParams["add_request"] = "1";
    }

    if (isReverseRequest) {
      addReverseRequestParams(requestParams)
    } else {
      addForwardRequestParams(requestParams)
    }

    return requestParams
  }

  private fun addForwardRequestParams(params: MutableMap<String, String>) {
    val sb = StringBuilder()
    for (p in this.queryParts) {
      if (sb.isNotEmpty()) {
        sb.append(this.queryPartSeparator)
      }
      sb.append(p)
    }
    params["q"] = sb.toString()

    if (bounds != null) {
      val param = StringBuilder()

      param.append(bounds.southwest!!.lat)
      param.append(",")
      param.append(bounds.southwest!!.lng)
      param.append(",")
      param.append(bounds.northeast!!.lat)
      param.append(",")
      param.append(bounds.northeast!!.lng)

      params["bounds"] = param.toString()
    }

    if (restrictToCountryCode != null) {
      params["countrycode"] = restrictToCountryCode!!
    }
  }

  private fun addReverseRequestParams(params: MutableMap<String, String>) {
    params["q"] = "$lat $lng"
  }
}
