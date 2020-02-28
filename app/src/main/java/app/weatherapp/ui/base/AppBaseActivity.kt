package app.weatherapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import app.weatherapp.BR
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AppBaseActivity<B : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity() {
    protected lateinit var viewModel: V
    protected lateinit var dataBinding: B
    abstract fun provideViewModelClass(): Class<V>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this, viewModelFactory)[provideViewModelClass()]
        dataBinding.setVariable(BR.viewModel, viewModel)
        dataBinding.executePendingBindings()
    }
}