package com.swayy.home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.repository.ListingRepository
import com.swayy.home.presentation.components.SearchWidgetState
import com.swayy.home.presentation.state.ListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.reflect.Array
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository
) : ViewModel() {

    init {
        getListing()
    }

    private val _listing = mutableStateOf(ListingState(isLoading = true))
    val listing: State<ListingState> = _listing

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    private val _searchString = mutableStateOf("")
    val searchString: State<String> = _searchString

    fun setSearchString(value: String) {
        _searchString.value = value
    }

    fun getListing(searchString: String = "") {
        viewModelScope.launch {
//            _listing.value = ListingState(isLoading = true) // Set loading state while fetching data

            when (val result = repository.getListings()) {
                is Resource.Error -> {
                    _listing.value = ListingState(isLoading = false, error = result.message)
                }

                is Resource.Success -> {
                    val filteredListing = if (searchString.isEmpty()) {
                        result.data ?: ArrayList()
                    } else {
                        result.data?.filter { item ->
                            // Customize the filter logic based on your data structure
                            item.location?.name?.contains(searchString, ignoreCase = true) == true
                        } ?: ArrayList()
                    }
                    _listing.value = ListingState(isLoading = false, listing = ArrayList(filteredListing))
                }

                else -> {
                    // Handle other resource types if necessary
                }
            }
        }
    }

//    fun getListing() {
//        viewModelScope.launch {
//            when (val result = repository.getListings()) {
//                is Resource.Error -> {
//                    _listing.value = ListingState(isLoading = false, error = result.message)
//                }
//
//                is Resource.Success -> {
//                    _listing.value = ListingState(isLoading = false, listing = result.data ?: ArrayList())
//                }
//
//                else -> {
//                    listing
//                }
//            }
//        }
//    }
}