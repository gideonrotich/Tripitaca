package com.swayy.home.presentation.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swayy.home.R
import com.swayy.home.presentation.ListingViewModel
import com.swayy.home.presentation.components.Header
import com.swayy.home.presentation.components.ListingItem
import com.swayy.home.presentation.components.MainAppBar
import com.swayy.home.presentation.components.SearchWidgetState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: ListingViewModel = hiltViewModel(),
    navigateListingDetails: (String, String) -> Unit,
    navigateSettings: () -> Unit,
) {

    val state = viewModel.listing.value
    val searchWidgetState by viewModel.searchWidgetState
    val searchString by viewModel.searchString
    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.material3.MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(navigateSettings)

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    MainAppBar(
                        searchWidgetState = searchWidgetState,
                        searchStringState = searchString,
                        onTextChange = {
                            viewModel.setSearchString(it)
                        },
                        onCloseClicked = {
                            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                            viewModel.getListing()
                        },
                        onSearchClicked = { _ ->
                            keyboardController?.hide()
                            viewModel.getListing(viewModel.searchString.value)
                        },
                        onSearchTriggered = {
                            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp, top = 0.dp,),
                backgroundColor = MaterialTheme.colorScheme.primary
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.inverseOnSurface)
                ) {
                    Column(
                        modifier = Modifier

                            .fillMaxSize()

                    ) {

                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Text(
                                text = "Recommended Listings",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(start = 13.dp, top = 10.dp, bottom = 10.dp),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Image(
                                painter = painterResource(id = R.drawable.check),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically),
                                contentScale = ContentScale.Crop
                            )
                        }

                        LazyVerticalGrid(
                            GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, top = 2.dp, end = 8.dp)
                        ) {
                            items(state.listing) { listing ->
                                ListingItem(
                                    listing = listing,
                                    navigateListingDetails = navigateListingDetails
                                )
                            }
                        }
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // An Error has occurred
                    if (!state.isLoading && state.error != null) {
                        Text(
                            text = state.error,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }

            }

        }


    }
}

