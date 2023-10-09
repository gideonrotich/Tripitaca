package com.swayy.core.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    val WALLET_ADDRESS = stringPreferencesKey("wallet_address")
    val IMAGE_ADDRESS = stringPreferencesKey("image_address")
    val NAME_ADDRESS = stringPreferencesKey("name_address")
    const val GOOGLE_PREFERENCES = "GOOGLE_PREFERENCES"
}