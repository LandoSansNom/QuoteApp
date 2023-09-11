package com.cherlan.quote.data.remote

import com.cherlan.quote.data.model.Quote
import retrofit2.http.GET

interface ApiCall {
    @GET(ApiDetails.QuotesEndPoint)
    suspend fun getGuotes(): List<Quote>
}