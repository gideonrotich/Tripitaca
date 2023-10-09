package com.swayy.home.presentation.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.swayy.core.viewmodel.ConnectWalletViewModel
import com.swayy.home.R
import java.util.Calendar

@Composable
fun Header(
    navigateSettings: () -> Unit,
) {

    val viewModel: ConnectWalletViewModel = hiltViewModel()

    val image = viewModel.image.collectAsState().value
    val name = viewModel.name.collectAsState().value

    Box(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(modifier = Modifier.padding(top = 50.dp, start = 20.dp)) {

            Card ( modifier = Modifier.size(53.dp),
                shape = RoundedCornerShape(100.dp)
            ){
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = getGreetingMessage(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.surface,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
            Spacer(modifier = Modifier.weight(2f))
            Image(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = {
                        navigateSettings()
                    }),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(20.dp))
        }

    }

}

fun getGreetingMessage():String{
    val c = Calendar.getInstance()
    val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

    return when (timeOfDay) {
        in 0..11 -> "Good morning"
        in 12..15 -> "Good afternoon"
        in 16..20 -> "Good evening"
        in 21..23 -> "Good night"
        else -> "Hello"
    }
}