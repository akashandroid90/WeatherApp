package lib.apidomain.repository

import lib.apidata.data.WeatherData
import lib.apidata.repository.HomeRepo
import lib.apidata.response.Result
import lib.apidomain.network.HomeApi
import retrofit2.await

class HomeRepoImpl(private val api: HomeApi) : HomeRepo {
    override suspend fun getWeatherData(city: String, appId: String): Result<WeatherData?> {
        return try {
            Result.Success(api.getWeatherData(city, appId).await())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}