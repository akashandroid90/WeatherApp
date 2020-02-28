package lib.apidomain.network

import lib.apidata.data.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  provides methods to fetch data from network
 */

interface HomeApi {
    @GET("/data/2.5/weather")
    fun getWeatherData(@Query("q") city: String, @Query("APPID") appId: String, @Query("units") units: String = "metric"): Call<WeatherData>
}
