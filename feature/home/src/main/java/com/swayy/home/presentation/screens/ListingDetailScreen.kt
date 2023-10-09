package com.swayy.home.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.swayy.core.R
import com.swayy.home.presentation.ListingViewModel
import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListingDetailScreen(
    listing: String,
    name: String,
    navigateBack: () -> Unit,
    viewModel: ListingViewModel = hiltViewModel()
) {
    val state = viewModel.listing.value

    Box(Modifier.fillMaxSize()) {


        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .wrapContentSize()
            ) {
                Row(
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 12.dp,
                        top = 40.dp,
                        bottom = 12.dp
                    )
                ) {

                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(com.swayy.home.R.drawable.baseline_arrow_back_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }

                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(2f))
                }

            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    val current = state.listing.filter { it._id == listing }

                    current.forEach { data ->
                        Column(modifier = Modifier.wrapContentSize()) {
                            Row(modifier = Modifier.padding(16.dp)) {
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.height(200.dp)
                                ) {
                                    Image(
                                        painter = rememberImagePainter(data.photos.first()),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }


                            }
                            Column() {
                                Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                                    val image = data.photos.drop(1)
                                    image.forEach { item ->
                                        Image(
                                            painter = rememberImagePainter(item),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(80.dp)
                                                .padding(10.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {


                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "About the place",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )
                                        Text(
                                            text = data.price.currency + " " + data.price.amount + "/night",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = data.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Listing Amenities",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 6.dp, end = 6.dp, top = 20.dp),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.person),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = data.details.guests.toString() + " " + "Guests",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                        Row(modifier = Modifier.padding(end = 30.dp)) {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.bathtub),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = data.details.bath.toString() + " " + "Bathrooms",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically),
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 6.dp, end = 6.dp),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.bedroom),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = data.details.bedroom.toString() + " " + "Bedrooms",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                        Row(modifier = Modifier.padding(end = 30.dp)) {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.bed),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = data.details.beds.toString() + " " + "Bed",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically),
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Other Amenities",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )

                                    val itemHeight = 40.dp

                                    val numColumns = 2 // Number of columns in the grid

                                    val numRows =
                                        (data.amenities.size + numColumns - 1) / numColumns
                                    val totalHeight = (numRows * itemHeight).coerceAtLeast(0.dp)

                                    LazyVerticalGrid(
                                        GridCells.Fixed(2),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(totalHeight)
                                            .padding(start = 8.dp, top = 0.dp, bottom = 0.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        contentPadding = PaddingValues(10.dp)
                                    ) {
                                        items(data.amenities) { info ->
                                            Text(
                                                text = info,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Start,
                                                maxLines = 1,
                                                modifier = Modifier.padding(6.dp)
                                            )
                                        }

                                    }

                                    Spacer(modifier = Modifier.height(0.dp))
                                    Text(
                                        text = "Where you'll be",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    val myLocation = LatLng(data.location.lat, data.location.lng)
                                    val cameraPositionState = rememberCameraPositionState {
                                        position = CameraPosition.fromLatLngZoom(myLocation, 29f)
                                    }
                                    GoogleMap(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .padding(12.dp),
                                        cameraPositionState = cameraPositionState,
                                        googleMapOptionsFactory = {
                                            val options =
                                                GoogleMapOptions()
                                            options.compassEnabled(true)
                                            options.zoomControlsEnabled(false)
                                            options.rotateGesturesEnabled(true)
                                            options.tiltGesturesEnabled(true)
                                            options.maxZoomPreference(18f)
                                            options.minZoomPreference(10f)
                                            options
                                        }
                                    ) {

                                        Marker(
                                            state = rememberMarkerState(position = myLocation),
                                            title = "Location based marker",
                                            snippet = "Marker in ${data.location.name}",
                                            icon = BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_ORANGE
                                            )
                                        )

                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Booking Availability",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,

                                        )

                                    BookingCalendar(bookedDates = data.bookedDates)

                                    androidx.compose.material.Button(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .padding(
                                                start = 30.dp,
                                                end = 30.dp,
                                                top = 12.dp,
                                                bottom = 12.dp
                                            )
                                            .fillMaxWidth()
                                            .height(50.dp)
                                            ,
                                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)

                                        ) {
                                        Text(
                                            text = "Book now",
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .align(Alignment.CenterVertically),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.surface,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = "House Rules",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,

                                        )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.baseline_access_time_filled_24),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(
                                                text = "Checkin: After " + data.rules.checkIn,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1,

                                                )
                                        }

                                        Row {
                                            Image(
                                                painter = painterResource(id = com.swayy.home.R.drawable.baseline_access_time_filled_24),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .align(Alignment.CenterVertically),
                                                contentScale = ContentScale.Crop,
                                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(
                                                text = "Checkout : " + data.rules.checkOut,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1,

                                                )
                                        }

                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(
                                        )
                                    ) {
                                        Text(
                                            text = "Hosted by " + data.user.firstName + " " + data.user.lastName + " :",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            maxLines = 1
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Card(
                                            modifier = Modifier.size(60.dp),
                                            shape = RoundedCornerShape(100.dp)
                                        ) {
                                            Image(
                                                painter = rememberImagePainter(data.user.profilePictureURL),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                                            Text(
                                                text = "Followers" + " : " + data.user.followers,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Following" + " : " + data.user.following,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(40.dp))
                                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                                            Text(
                                                text = "Ratings" + " : " + data.user.rating,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Reviews" + " : " + data.user.reviews,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Center,
                                                maxLines = 1
                                            )
                                        }
                                    }

                                    Row(modifier = Modifier.height(100.dp)) {

                                    }
                                }

                            }

                        }

                    }
                }


            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun BookingCalendar(bookedDates: List<String>) {
    // Calculate the current month and year
    val currentDate = Calendar.getInstance()
    val currentMonth = currentDate.get(Calendar.MONTH)
    val currentYear = currentDate.get(Calendar.YEAR)

    // Create a list of months to display
    val monthsToDisplay = (0..11).map { monthOffset ->
        val month = (currentMonth + monthOffset) % 12
        val year = currentYear + (currentMonth + monthOffset) / 12
        Pair(month, year)
    }

    // Initialize a HorizontalPagerState with the count of months to display
    val pagerState = rememberPagerState(initialPage = 0)

    // State to track the selected date
    var selectedDate by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the month and year at the top
        val (currentDisplayMonth, currentDisplayYear) = monthsToDisplay[pagerState.currentPage]
        val displayMonthYearText = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            .format(Calendar.getInstance().apply {
                set(currentDisplayYear, currentDisplayMonth, 1)
            }.time)

        Text(
            text = displayMonthYearText,
            modifier = Modifier.padding(16.dp),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // Display the day initials (M, T, W, T, F, S, S)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val dayInitials = listOf("M", "T", "W", "T", "F", "S", "S")
            dayInitials.forEach { initial ->
                Text(
                    text = initial,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // HorizontalPager for the calendar
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            count = 3
        ) { page ->
            val (month, year) = monthsToDisplay[page]
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            val firstDay = Calendar.getInstance()
            firstDay.set(year, month, 1)
            val daysInMonth = firstDay.getActualMaximum(Calendar.DAY_OF_MONTH)

            CalendarPage(
                month = month,
                year = year,
                bookedDates = bookedDates,
                dateFormat = dateFormat,
                daysInMonth = daysInMonth,
                selectedDate = selectedDate,
                onDateClick = { date ->
                    if (!bookedDates.contains(date)) {
                        selectedDate = date
                    }
                }
            )
        }

        // Display the selected date
        selectedDate?.let { date ->
            Text(
                text = "Selected Date: $date",
                modifier = Modifier.padding(16.dp),
                color = Color.Green,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CalendarPage(
    month: Int,
    year: Int,
    bookedDates: List<String>,
    dateFormat: SimpleDateFormat,
    daysInMonth: Int,
    selectedDate: String?,
    onDateClick: (String) -> Unit
) {
    val calendarModifier = Modifier.padding(16.dp)
    val calendar = Calendar.getInstance()

    val itemHeight = 40.dp

    val numColumns = 7 // Number of columns in the grid

// Calculate the number of rows needed to display all days
    val numRows =
        (daysInMonth + (calendar.get(Calendar.DAY_OF_WEEK) - 1) + (numColumns - 1)) / numColumns

// Calculate the total height based on the number of rows and itemHeight
    val totalHeight = (numRows * itemHeight).coerceAtLeast(0.dp)

    LazyVerticalGrid(
        columns = GridCells.Fixed(7), // Display 7 columns
        modifier = calendarModifier.height(totalHeight),

        ) {
        // Add empty cells for days before the first day of the month
        repeat(calendar.get(Calendar.DAY_OF_WEEK) - 1) {
            item { Spacer(modifier = Modifier.fillMaxSize()) }
        }

        for (day in 1..daysInMonth) {
            val currentDate = Calendar.getInstance()
            currentDate.set(year, month, day)
            val dateStr = dateFormat.format(currentDate.time)

            val isBooked = bookedDates.contains(dateStr)
            val isClickable = !isBooked
            val isSelected = selectedDate == dateStr

            val textColor = if (isBooked) Color.Gray else Color.Black
            val backgroundColor =
                if (isSelected) Color.Green else if (isClickable) Color.White else Color.LightGray

            item {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(backgroundColor)
                        .clickable {
                            if (isClickable) {
                                onDateClick(dateStr)
                            }
                        }
                        .padding(4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = day.toString(),
                        color = textColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            // Add empty cells for days after the last day of the month
            if (day == daysInMonth && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                repeat(7 - calendar.get(Calendar.DAY_OF_WEEK)) {
                    item { Spacer(modifier = Modifier.fillMaxSize()) }
                }
            }
        }
    }
}
