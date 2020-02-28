package app.weatherapp.di.module

import app.weatherapp.ui.home.HomeActivity
import app.weatherapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * provides builder methods for Dagger
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}