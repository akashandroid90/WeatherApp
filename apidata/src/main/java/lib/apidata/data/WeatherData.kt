package lib.apidata.data

data class WeatherData(
    val coord: Coordinates,
    val weather: MutableList<Weather>,
    val base: String,
    val main: MainData?,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Int
)