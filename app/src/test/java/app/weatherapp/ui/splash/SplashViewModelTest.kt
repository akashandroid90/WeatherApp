package app.weatherapp.ui.splash

import app.weatherapp.constant.ClickActionEvents
import app.weatherapp.ui.base.BaseUnitTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SplashViewModelTest : BaseUnitTest<SplashViewModel>() {
    @Before
    fun setUp() {
        viewModel = SplashViewModel()
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun testNavigateToHomeScreenSuccess() = runBlockingTest {
        Assert.assertNotNull(viewModel)
        viewModel?.loadScreen()
        Assert.assertNotNull(viewModel?.navigateScreeen?.value)
        Assert.assertEquals(viewModel?.navigateScreeen?.value, ClickActionEvents.HOME)
    }

    @Test
    fun testNavigateToHomeScreenFail() = runBlockingTest {
        Assert.assertNotNull(viewModel)
        viewModel?.loadScreen()
        Assert.assertNotEquals(viewModel?.navigateScreeen?.value, "")
    }
}
