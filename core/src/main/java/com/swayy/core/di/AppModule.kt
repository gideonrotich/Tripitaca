package com.swayy.core.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.swayy.core.data.datastore.AppSettingsManager
import com.swayy.core.data.datastore.GoogleTimePreferences
import com.swayy.core.data.datastore.ThemeSettingsManager
import com.swayy.core.data.repository.WalletRepositoryImpl
import com.swayy.core.domain.repository.WalletRepository
import com.swayy.core.util.Constants.GOOGLE_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val ACRA_SHARED_PREFS_NAME = "acra_shared_pref"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // appTheme datastore
    @Provides
    @Singleton
    fun provideThemeSettingsManager(@ApplicationContext context: Context) =
        ThemeSettingsManager(context)

    // settings datastore
    @Provides
    @Singleton
    fun provideAppSettingsManager(@ApplicationContext context: Context) =
        AppSettingsManager(context)

    @Provides
    @Singleton
    fun providesWalletRepository(
        googleTimePreferences: GoogleTimePreferences
    ): WalletRepository {
        return WalletRepositoryImpl(
            googlePreferences = googleTimePreferences
        )
    }

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(GOOGLE_PREFERENCES)
            }
        )

    @Provides
    @Singleton
    fun provideGoogleTimePreferences(dataStore: DataStore<Preferences>) =
        GoogleTimePreferences(dataStore)

}