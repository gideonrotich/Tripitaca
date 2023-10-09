package com.swayy.home.domain.model

data class EmergencyBooking(
    val bookable: Boolean,
    val descripition: String,
    val discountPercentage: Int,
    val selfCheckin: Boolean
)