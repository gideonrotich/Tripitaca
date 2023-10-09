
package com.swayy.core.data.datastore

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.swayy.core.domain.model.GooglePlanPreference
import com.swayy.core.util.Constants.IMAGE_ADDRESS
import com.swayy.core.util.Constants.NAME_ADDRESS
import com.swayy.core.util.Constants.WALLET_ADDRESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoogleTimePreferences(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveGooglePlanPreferences(
        walletAddress: String,
        image:String,
        name:String

    ) {
        dataStore.edit { preferences ->
            preferences[WALLET_ADDRESS] = walletAddress
            preferences[IMAGE_ADDRESS] = image
            preferences[NAME_ADDRESS] = name
        }
    }

    val googlePlanPreferences: Flow<GooglePlanPreference?> = dataStore.data.map { preferences ->
        GooglePlanPreference(
            walletAddress = preferences[WALLET_ADDRESS] ?: "0",
            image = preferences[IMAGE_ADDRESS] ?: "",
            name = preferences[NAME_ADDRESS] ?: ""
        )
    }

}
