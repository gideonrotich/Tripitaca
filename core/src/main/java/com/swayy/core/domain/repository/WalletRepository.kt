package com.swayy.core.domain.repository

import com.swayy.core.domain.model.GooglePlanPreference
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    val hasGooglePlanPref: Flow<GooglePlanPreference?>

    suspend fun saveGooglePreferences(
        walletAddress: String,
        image: String,
        name:String
    )

}