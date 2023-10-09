package com.swayy.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.swayy.home.R
import com.swayy.home.domain.model.Listing

@Composable
fun ListingItem(
    listing: Listing,
    navigateListingDetails: (String,String) -> Unit,
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(250.dp)
            .padding(6.dp)
            .clickable(onClick = {
                navigateListingDetails(listing._id,listing.name)
            }),
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Image(
                    painter = rememberImagePainter(listing.photos.first()),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.BottomEnd)
                        .padding(4.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = MaterialTheme.colorScheme.surface
                ) {
                    Row (modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp)){
                        Image(
                            painter = painterResource(id = R.drawable.pin),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.CenterVertically),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(0.dp))
                        Text(
                            text = listing.location.name,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(64.dp)
                                .align(Alignment.CenterVertically),
                            maxLines = 1
                        )
                    }

                }

            }

            Text(
                text = listing.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = listing.details.guests.toString() + "Guests",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.bathtub),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = listing.details.bath.toString() + "Bathrooms",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
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
                        painter = painterResource(id = R.drawable.bedroom),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = listing.details.bedroom.toString() + "Bedroom",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.bed),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = listing.details.beds.toString() + "Bed",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = listing.price.currency + " " + listing.price.amount,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
    }

}