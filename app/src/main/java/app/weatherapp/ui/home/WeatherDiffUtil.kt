package app.weatherapp.ui.home

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import lib.apidata.data.Weather

class WeatherDiffUtil : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return TextUtils.equals(oldItem.main, newItem.main)
                && TextUtils.equals(oldItem.description, newItem.description)
                && TextUtils.equals(oldItem.icon, newItem.icon)
    }
}