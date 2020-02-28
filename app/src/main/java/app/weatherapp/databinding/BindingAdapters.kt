package app.weatherapp.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/**
 * BindingAdapters.kt contains Databinding conversions and adapters
 */

@BindingAdapter("image")
fun loadImage(
    imageView: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(imageView).load("http://openweathermap.org/img/wn/$it@2x.png").into(imageView)
    }
}

@BindingAdapter("addItemDecoration")
fun addItemDecoration(view: RecyclerView, vertical: Boolean) {
    if (vertical)
        view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
    else view.addItemDecoration(
        DividerItemDecoration(
            view.context,
            DividerItemDecoration.HORIZONTAL
        )
    )
}

@BindingAdapter("temperature")
fun showTemperature(view: TextView, value: String?) {
    value?.let {
        view.text = StringBuilder().append(it).append("\u2103")
    }
}

@BindingAdapter(value = ["mintemperature", "maxtemperature"])
fun showTemperatureRange(view: TextView, min: String?, max: String?) {
    view.text =
        StringBuilder().append(min).append("\u2103").append("/").append(max).append("\u2103")
}