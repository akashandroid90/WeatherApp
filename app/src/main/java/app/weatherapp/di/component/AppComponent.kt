package app.weatherapp.di.component

import app.weatherapp.AppApplication
import app.weatherapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 *  initialize modules used for Dagger to provide instance to application
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ApiRepoModule::class, ApiModule::class, NetworkModule::class, AppModule::class, ViewModelModule::class, ActivityModule::class]
)
interface AppComponent : AndroidInjector<AppApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<AppApplication>
}