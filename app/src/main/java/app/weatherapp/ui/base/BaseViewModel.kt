package app.weatherapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.weatherapp.constant.ClickActionEvents
import app.weatherapp.utils.SingleLiveEvent


abstract class BaseViewModel : ViewModel() {
    @ClickActionEvents
    val navigateScreeen = SingleLiveEvent<String>()
    val showProgress = MutableLiveData(false)
}
