package com.jamiescode.showcase.settings.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamiescode.showcase.navigation.AppNavigator
import com.jamiescode.showcase.navigation.Destinations
import com.jamiescode.showcase.settings.data.UserSettings
import com.jamiescode.showcase.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
    @Inject
    constructor(
        private val settingsRepository: SettingsRepository,
        private val appNavigator: AppNavigator,
    ) : ViewModel() {
        private val stateMutableLiveData: MutableLiveData<SettingsState> by lazy {
            MutableLiveData<SettingsState>(SettingsState.Initial)
        }
        val stateLiveData = stateMutableLiveData as LiveData<SettingsState>

        fun load() {
            viewModelScope.launch {
                val userSettings = settingsRepository.getUserSettings()
                stateMutableLiveData.postValue(SettingsState.Loaded(userSettings))
            }
        }

        fun setDailyReminderNotification(checked: Boolean) {
            viewModelScope.launch {
                settingsRepository.setDailyReminderNotifications(checked)
            }
            load()
        }

        fun setUnlockWithBiometrics(checked: Boolean) {
            viewModelScope.launch {
                settingsRepository.setUnlockWithBiometrics(checked)
            }
            load()
        }

        fun navigateToOpenSourceLicenses() {
            appNavigator.navigateTo(Destinations.OpenSourceLicenses)
        }

        fun navigateToFeedbackForm() {
            appNavigator.navigateTo(Destinations.FeedbackForm)
        }

        fun navigateToBuyMeACoffee() {
            appNavigator.navigateTo(Destinations.BuyMeACoffee)
        }

        fun navigateToUnderConstruction() {
            appNavigator.navigateTo(Destinations.UnderConstruction)
        }

        sealed class SettingsState {
            data object Initial : SettingsState()

            data class Loaded(
                val userSettings: Flow<UserSettings>,
            ) : SettingsState()
        }
    }
