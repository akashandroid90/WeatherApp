package app.weatherapp.di.module

import android.content.Context
import app.weatherapp.AppApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * provides application level instances
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: AppApplication): Context {
        return application.baseContext
    }
}