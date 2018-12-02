package com.ktopencage

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class BooleanSerializer : JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
  override fun serialize(arg0: Boolean?, arg1: Type, arg2: JsonSerializationContext): JsonElement {
    return JsonPrimitive(java.lang.Boolean.TRUE == arg0)
  }

  @Throws(JsonParseException::class)
  override fun deserialize(arg0: JsonElement, arg1: Type, arg2: JsonDeserializationContext): Boolean? {
    return arg0.asInt == 1
  }
}
