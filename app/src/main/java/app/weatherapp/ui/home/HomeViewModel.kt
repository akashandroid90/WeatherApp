package app.weatherapp.ui.home

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import app.weatherapp.BR
import app.weatherapp.BuildConfig
import app.weatherapp.R
import app.weatherapp.ui.base.BaseViewModel
import app.weatherapp.utils.Logger
import app.weatherapp.utils.toast
import kotlinx.coroutines.launch
import lib.apidata.data.Weather
import lib.apidata.data.WeatherData
import lib.apidata.repository.HomeRepo
import lib.apidata.response.Result
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(val context: Context, val api: HomeRepo) :
    BaseViewModel() {
    val data = ObservableField<WeatherData>()
    val dataList = DiffObservableList(WeatherDiffUtil())
    val city = ObservableField<String>()
    val itemBinding by lazy {
        ItemBinding.of<Weather>(BR.data, R.layout.adapter_weather)
    }

    fun fetchWeather() {
        viewModelScope.launch {
            val city = city.get()
            if (city.isNullOrBlank()) {
                context.getString(R.string.enter_valid_city).toast(context)
                return@launch
            }
            showProgress.postValue(true)
            when (val result = api.getWeatherData(city, BuildConfig.WEATHER_APP_ID)) {
                is Result.Success -> result.value?.let {
                    data.set(it)
                    dataList.update(it.weather)
                }
                is Result.Error -> {
                    data.set(null)
                    dataList.update(Collections.emptyList())
                    context.getString(R.string.retry).toast(context)
//                    result.exception.message?.let { Logger.e(it) }
                }
            }
            showProgress.postValue(false)
        }
    }
}