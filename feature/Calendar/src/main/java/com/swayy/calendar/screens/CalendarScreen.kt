package com.swayy.calendar.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swayy.core.R

@Composable
fun CalendarScreen() {


    Box(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, top = 50.dp, end = 14.dp)) {
            Text(
                text = "Calendar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.baseline_tune_24),
                contentDescription = null,
                modifier = Modifier
                    .size(17.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = null,
                modifier = Modifier
                    .size(17.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

        }

    }
}