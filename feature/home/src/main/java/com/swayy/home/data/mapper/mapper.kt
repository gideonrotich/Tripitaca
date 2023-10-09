package com.swayy.home.data.mapper

import com.swayy.home.domain.model.Listing
import com.swayy.home.domain.model.ListingResponseItem

internal fun ListingResponseItem.toDomain(): Listing {
    return Listing(
        __v,
        _id,
        amenities,
        bookedDates,
        bookings,
        cancellationPolicy,
        created,
        description,
        details,
        emergencyBooking,
        location,
        name,
        noOfRates,
        photos,
        point,
        price,
        rating,
        recommendations,
        reviews,
        rules,
        space,
        status,
        tags,
        timeStamp,
        type,
        uniqueType,
        user,
        video
    )
}