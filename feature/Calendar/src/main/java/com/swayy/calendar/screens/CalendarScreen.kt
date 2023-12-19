package com.swayy.calendar.screens

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.swayy.core.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun CalendarScreen() {


    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, top = 60.dp, end = 14.dp)
            ) {
                Text(
                    text = "Calendar",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.weight(0.5f))

                Image(
                    painter = painterResource(id = R.drawable.baseline_tune_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(14.dp))

                Image(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )

            }


            Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, top = 0.dp, end = 14.dp)){
                Demo_ExposedDropdownMenuBox()
                Spacer(modifier = Modifier.weight(0.5f))

                Image(
                    painter = painterResource(id = R.drawable.baseline_tune_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(14.dp))

                Image(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .wrapContentHeight()
            ) {

                LazyRow() {
                    item {
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray.copy(alpha = .4f))
                                .padding(0.dp)
                                .width(1200.dp)
                                .height(554.dp)
                        ) {
                            Row(modifier = Modifier.wrapContentHeight()) {
                                Row(
                                    modifier = Modifier
                                        .height(130.dp)
                                ) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Listings",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .align(Alignment.CenterVertically)
                                    )

                                    Spacer(modifier = Modifier.width(45.dp))
                                    Divider(
                                        color = Color.LightGray.copy(alpha = .5f),
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                    )
                                }

                                val currentMonthDays = getCurrentMonthDays()

                                Row(
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .background(Color.White)
                                ) {
                                    currentMonthDays.forEach { dayOfMonth ->
                                        Row(
                                            modifier = Modifier
                                                .height(130.dp)
                                                .width(80.dp)
                                                .background(Color.LightGray.copy(alpha = .4f))
                                        ) {
                                            DayDateItem(dayOfMonth = dayOfMonth)
                                            Spacer(modifier = Modifier.width(8.dp))
                                        }
                                        Divider(
                                            color = Color.LightGray,
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(1.dp)
                                        )
                                    }
                                }
                            }


                            // Bookings
                            val bookingOne = listOf(
                                Booking(
                                    name = "Dakata David",
                                    startDate = 1,
                                    endDate = 3,
                                    "Tripitaca"
                                ),
                                Booking(name = "Booking", startDate = 4, endDate = 5, "Airbnb"),
                                Booking(name = "Brian", startDate = 9, endDate = 11, "Booking.com"),
                                Booking(name = "Kelvin", startDate = 20, endDate = 30, "Tripitaca")
                            )

                            val bookingTwo = listOf(
                                Booking(
                                    name = "Other Bookings",
                                    startDate = 1,
                                    endDate = 2,
                                    "Other booking"
                                ),
                                Booking(
                                    name = "Booking.com",
                                    startDate = 6,
                                    endDate = 7,
                                    "Booking.com"
                                ),
                                Booking(name = "Collins", startDate = 10, endDate = 13, "Tripitaca"),
                                Booking(name = "Mannu", startDate = 20, endDate = 30, "Airbnb")
                            )

                            val bookingThree = listOf(
                                Booking(
                                    name = "Dakata David",
                                    startDate = 2,
                                    endDate = 3,
                                    "Tripitaca"
                                ),
                                Booking(
                                    name = "Airbnb",
                                    startDate = 8,
                                    endDate = 9,
                                    "Airbnb"
                                ),
                                Booking(
                                    name = "Collins",
                                    startDate = 25,
                                    endDate = 29,
                                    "Tripitaca"
                                ),
                                Booking(name = "Mannu", startDate = 20, endDate = 24, "Airbnb")
                            )

                            val bookingFour = listOf(
                                Booking(name = "Airbnb", startDate = 1, endDate = 3, "Airbnb"),
                                Booking(
                                    name = "Booking.com",
                                    startDate = 5,
                                    endDate = 7,
                                    "Booking.com"
                                ),
                                Booking(
                                    name = "Collins",
                                    startDate = 10,
                                    endDate = 13,
                                    "Tripitaca"
                                ),
                                Booking(name = "Mannu", startDate = 17, endDate = 20, "Airbnb")
                            )

                            val bookingFive = listOf(
                                Booking(
                                    name = "Booking.com",
                                    startDate = 1,
                                    endDate = 4,
                                    "Booking.com"
                                ),
                                Booking(
                                    name = "Booking.com",
                                    startDate = 6,
                                    endDate = 7,
                                    "Booking.com"
                                ),
                                Booking(name = "Collins", startDate = 9, endDate = 10, "Tripitaca"),
                                Booking(name = "Mannu", startDate = 16, endDate = 18, "Airbnb")
                            )

                            val list = listOf(
                                Listing("Seaside Cottage", bookingOne),
                                Listing("Mali apartments", bookingTwo),
                                Listing("Lui Homes", bookingThree),
                                Listing("City View", bookingFour),
                                Listing("Zuri Homes", bookingFive),
                            )


                            Box(modifier = Modifier.padding(top = 130.dp)) {
                                LazyColumn() {
                                    items(list) {
                                        Divider(
                                            color = Color.LightGray,
                                            modifier = Modifier
                                                .height(1.dp)
                                        )
                                        Row(modifier = Modifier.wrapContentWidth()) {

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .width(140.dp)
                                                    .padding(14.dp)
                                            ) {
                                                Text(
                                                    text = it.listngName,
                                                    fontSize = 14.sp,
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    color = MaterialTheme.colorScheme.onSurface,
                                                    modifier = Modifier
                                                        .padding(10.dp)
                                                        .align(Alignment.CenterVertically)
                                                )
                                            }

                                            var accumulatedWidth = 0
                                            Box {

                                                it.books.forEach {
                                                    val startPosition = (it.startDate - 1) * 80
                                                    val width = (it.endDate - it.startDate + 1) * 80

                                                    Row(modifier = Modifier.height(76.dp)) {
                                                        Text(
                                                            text = it.name,
                                                            fontSize = 13.sp,
                                                            style = MaterialTheme.typography.bodyLarge,
                                                            color = Color.White,
                                                            modifier = Modifier
                                                                .width(width.dp)
                                                                .offset(x = startPosition.dp)
                                                                .clip(RoundedCornerShape(20.dp))
                                                                .background(getPlatformColour(it.platform))
                                                                .padding(10.dp)
                                                                .align(Alignment.CenterVertically),
                                                            textAlign = TextAlign.Center

                                                        )
                                                    }

                                                }


                                            }

                                        }
                                        Divider(
                                            color = Color.LightGray.copy(alpha = .5f),
                                            modifier = Modifier
                                                .height(1.dp)
                                        )

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("All", "Mali Apartments", "Lui Homes", "City View", "Seaside Cottage")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .background(Color.White)
            .width(155.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.background(Color.White)
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun getPlatformColour(name: String): Color {
    val colour = when (name) {
        "Tripitaca" -> MaterialTheme.colorScheme.error
        "Airbnb" -> Color.Magenta
        "Booking.com" -> Color.Blue
        "Other booking" -> MaterialTheme.colorScheme.onErrorContainer
        else -> Color.Gray
    }
    return colour
}

data class Booking(
    val name: String,
    val startDate: Int,
    val endDate: Int,
    val platform: String
)

data class Listing(
    val listngName: String,
    val books: List<Booking>,
)


@Composable
fun NameItem(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Gray)
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DayDateItem(dayOfMonth: Int) {
    val dayOfWeek = getDayOfWeek(dayOfMonth)

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = .0f))
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(4.dp)
                .width(30.dp)
        ) {
            Text(
                text = dayOfWeek,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}

@Composable
fun getCurrentMonthDays(): List<Int> {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentYear = calendar.get(Calendar.YEAR)
    val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val currentMonthDays = mutableListOf<Int>()
    for (day in 1..lastDay) {
        currentMonthDays.add(day)
    }
    return currentMonthDays
}

@Composable
fun getDayOfWeek(day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, day)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
}


