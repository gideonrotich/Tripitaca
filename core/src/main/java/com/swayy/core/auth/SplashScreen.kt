package com.swayy.core.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swayy.core.R
import com.swayy.core.viewmodel.ConnectWalletViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navigateHome: () -> Unit,
    navigateLogin: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val coroutineScope = rememberCoroutineScope()

        val viewModel: ConnectWalletViewModel = hiltViewModel()

        val walletAddress = viewModel.userWallet.collectAsState().value

        // A flag to track whether the navigation should happen
        var navigateToHome by remember { mutableStateOf(false) }

        var navigateToLogin by remember { mutableStateOf(false) }

        // Start a timer when the composable is first launched
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                delay(3000) // Adjust the duration as needed (in milliseconds)

                if (walletAddress == "0") {
                    navigateToLogin = true
                } else {
                    navigateToHome = true
                }
            }
        }


        if (navigateToHome) {
            navigateHome()
        }

        if (navigateToLogin) {
            navigateLogin()
        }

        Column (modifier = Modifier
            .align(Alignment.Center)){
            Image(
                painter = painterResource(id = R.drawable.maybe),
                contentDescription = null,
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}