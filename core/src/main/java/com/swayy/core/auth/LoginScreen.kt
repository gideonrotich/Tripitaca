package com.swayy.core.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.swayy.core.R
import com.swayy.core.viewmodel.ConnectWalletViewModel

@Composable
fun LoginScreen(
    navigateHome: () -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        val viewModel: ConnectWalletViewModel = hiltViewModel()

        val state = rememberOneTapSignInState()
        var fullName by remember { mutableStateOf("") }
        OneTapSignInWithGoogle(
            state = state,
            clientId = "992346807063-ol2oes1vt6ejtr82uugig95lo681f4fp.apps.googleusercontent.com",
            onTokenIdReceived = { tokenId ->
                val googleUser = getUserFromTokenId(tokenId)
                fullName =
                    googleUser.fullName.toString() // Set the fullName variable with the full name from GoogleUser
                Log.d("LOG", tokenId)

                viewModel.saveWallet(tokenId,googleUser.picture.toString(),fullName)

                navigateHome()

            },
            onDialogDismissed = { message ->
                Log.d("LOG", message)
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.home), contentDescription ="" ,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop)


            Column(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    )
            ) {
                androidx.compose.material3.Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    onClick = {

                        state.open()
                    },
                    enabled = !state.opened,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White
                    ),
                ) {
                    Image(
                        modifier = Modifier.padding(6.dp),
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Icon"
                    )

                    Text(
                        text = "Sign In with Google",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = 14.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


            }
        }

    }


}