package app.weatherapp.ui.home

import android.content.Context
import android.widget.Toast
import app.kotlinassignment.utils.MockResponse
import app.weatherapp.R
import app.weatherapp.di.module.ApiModule
import app.weatherapp.di.module.ApiRepoModule
import app.weatherapp.di.module.NetworkModule
import app.weatherapp.ui.base.BaseUnitTest
import app.weatherapp.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.io.File
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest : BaseUnitTest<HomeViewModel>() {
    private val mockWebServer = MockWebServer()
    @Before
    fun setUp() {
        mockWebServer.start()
        Dispatchers.setMain(TestCoroutineDispatcher())
        val networkModule = NetworkModule()
        val context = mock(Context::class.java)
        `when`(context.cacheDir).thenReturn(File(""))
        viewModel = HomeViewModel(
            context,
            ApiRepoModule().provideHomeRepoImpl(
                ApiModule().provideHomeApi(
                    networkModule.provideRetrofitInterface(
                        mockWebServer.url(
                            "/"
                        ).toString(), networkModule.providesOkHttpClient(context)
                    )
                )
            )
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadDataFail() {
        runBlocking {
            Assert.assertNotNull(viewModel)
            mockWebServer.enqueue(
                MockResponse.createMockResponse(
                    "api_response_fail",
                    HttpURLConnection.HTTP_BAD_REQUEST
                )
            )
            viewModel?.fetchWeather()?.join()
            viewModel?.dataList?.isEmpty()?.let {
                Assert.assertTrue(it)
            }
        }
    }

    @Test
    fun testLoadDataSuccess() {
        runBlocking {
            Assert.assertNotNull(viewModel)
            mockWebServer.enqueue(
                MockResponse.createMockResponse(
                    "api_response_success",
                    HttpURLConnection.HTTP_OK
                )
            )
            viewModel?.fetchWeather()?.join()
            Assert.assertNotNull(viewModel?.data)
        }
    }
}