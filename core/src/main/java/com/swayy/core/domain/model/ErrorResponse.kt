package com.swayy.core.domain.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("errors")
    val errors: List<String?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)
