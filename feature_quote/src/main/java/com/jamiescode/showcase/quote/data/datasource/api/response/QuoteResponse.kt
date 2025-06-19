package com.jamiescode.showcase.quote.data.datasource.api.response

import com.jamiescode.showcase.quote.data.datasource.api.model.QuoteDto
import com.jamiescode.showcase.quote.domain.model.Quote

typealias QuoteResponse = List<QuoteDto>

internal fun QuoteResponse?.toDomainModel(): List<Quote> =
    this?.map {
        Quote(
            text = it.quoteText,
            author = it.authorName,
        )
    } ?: emptyList()
