package app.weatherapp.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import app.weatherapp.R
import app.weatherapp.databinding.ActivityHomeBinding
import app.weatherapp.ui.base.AppBaseActivity
import app.weatherapp.utils.toast

class HomeActivity : AppBaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.activity_home
    override fun provideViewModelClass() = HomeViewModel::class.java
    private var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showProgress.observe(this, Observer {
            if (it) {
                dialog =
                    AlertDialog.Builder(this).setView(ProgressBar(this)).show().also { dialog ->
                        dialog.window?.setBackgroundDrawable(null)
                    }
            } else dialog?.dismiss()
        })
        viewModel.showMessage.observe(this, Observer {
            if (!it.isNullOrEmpty())
                it.toast(this)
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(dataBinding.toolbar)
        dataBinding.etEnterLocation.setOnEditorActionListener { _, actionId: Int, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.fetchWeather()
            }
            false
        }
    }
}