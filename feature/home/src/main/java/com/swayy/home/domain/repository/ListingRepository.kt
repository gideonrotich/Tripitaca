package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.home.domain.model.Listing
import com.swayy.home.domain.model.ListingResponseItem

interface ListingRepository {
    suspend fun getListings(): Resource<ArrayList<Listing>>
}