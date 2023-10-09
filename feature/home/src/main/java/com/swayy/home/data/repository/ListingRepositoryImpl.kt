package com.swayy.home.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayy.core.util.Resource
import com.swayy.core.util.safeApiCall
import com.swayy.home.data.mapper.toDomain
import com.swayy.home.domain.model.Listing
import com.swayy.home.domain.model.ListingResponseItem
import com.swayy.home.domain.repository.ListingRepository
import kotlinx.coroutines.Dispatchers

class ListingRepositoryImpl(
    private val context: Context
) : ListingRepository {
    override suspend fun getListings(): Resource<ArrayList<Listing>> {
        return safeApiCall(Dispatchers.IO) {
            val jsonString =
                context.assets.open("listings.json").bufferedReader().use { it.readText() }
            val response = HouseJsonParser.parseHousesFromJson(jsonString)
            val listingResponseItems = response.map { it.toDomain() }
            ArrayList(listingResponseItems)
        }
    }
}

object HouseJsonParser {
    fun parseHousesFromJson(jsonString: String): List<ListingResponseItem> {
        val gson = Gson()
        val listType = object : TypeToken<List<ListingResponseItem>>() {}.type
        return gson.fromJson(jsonString, listType)
    }
}