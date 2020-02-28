package app.weatherapp.ui.splash

import androidx.lifecycle.viewModelScope
import app.weatherapp.constant.ClickActionEvents
import app.weatherapp.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    fun loadScreen() {
        viewModelScope.launch {
            delay(1000)
            navigateScreeen.value = ClickActionEvents.HOME
        }
    }
}