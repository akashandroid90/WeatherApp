package lib.apidata.repository

import lib.apidata.data.WeatherData
import lib.apidata.response.Result

interface HomeRepo {
    suspend fun getWeatherData(city: String, appId: String): Result<WeatherData?>
}