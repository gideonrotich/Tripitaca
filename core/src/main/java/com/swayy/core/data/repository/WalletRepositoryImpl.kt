package com.swayy.core.data.repository

import android.content.Context
import com.swayy.core.data.datastore.GoogleTimePreferences
import com.swayy.core.domain.model.GooglePlanPreference
import com.swayy.core.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletRepositoryImpl(
    private val googlePreferences: GoogleTimePreferences,
) : WalletRepository {

    override suspend fun saveGooglePreferences(walletAddress: String,image:String,name:String) {
        googlePreferences.saveGooglePlanPreferences(
            walletAddress = walletAddress,
            image = image,
            name = name
        )
    }

    override val hasGooglePlanPref: Flow<GooglePlanPreference?>
        get() = googlePreferences.googlePlanPreferences
}
