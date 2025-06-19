package com.jamiescode.showcase.quote.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("q")
    val quoteText: String,
    @SerializedName("a")
    val authorName: String,
)
