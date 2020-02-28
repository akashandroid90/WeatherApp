package app.weatherapp.di.module

import dagger.Module
import dagger.Provides
import lib.apidata.repository.HomeRepo
import lib.apidomain.network.HomeApi
import lib.apidomain.repository.HomeRepoImpl
import javax.inject.Singleton

@Module
class ApiRepoModule {

    @Provides
    @Singleton
    fun provideHomeRepoImpl(api: HomeApi): HomeRepo =
        HomeRepoImpl(api)
}