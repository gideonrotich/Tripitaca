package com.swayy.home.presentation.state

import com.swayy.home.domain.model.Listing

data class ListingState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val listing: ArrayList<Listing> = ArrayList()
)
