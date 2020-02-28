package app.weatherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import app.weatherapp.R
import app.weatherapp.constant.ClickActionEvents
import app.weatherapp.databinding.ActivitySplashBinding
import app.weatherapp.ui.base.AppBaseActivity
import app.weatherapp.ui.home.HomeActivity

class SplashActivity : AppBaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun provideViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override val layoutId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateScreeen.observe(this, Observer {
            when (it) {
                ClickActionEvents.HOME -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.loadScreen()
    }
}
