package com.swayy.core.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.domain.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ConnectWalletViewModel @Inject constructor(
    private val repository: WalletRepository
) : ViewModel() {

    var walletType = MutableStateFlow("")
    var userWallet = MutableStateFlow("")
        private set

    var image = MutableStateFlow("")
        private set

    var name = MutableStateFlow("")
        private set

    init {
        RetrieveKey()
    }

    private fun RetrieveKey() {
        viewModelScope.launch {
            repository.hasGooglePlanPref.collectLatest {
                userWallet.value = it?.walletAddress.toString()
                image.value = it?.image.toString()
                name.value = it?.name.toString()
            }
        }
    }

    fun saveWallet(wallet:String,image:String,name:String){
        viewModelScope.launch {
            repository.saveGooglePreferences(
                walletAddress = wallet,
                image = image,
                name = name
            )
        }
    }

}