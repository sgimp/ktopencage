package com.ktopencage

class ResponseException(var code: Int, override var message: String) : Exception()