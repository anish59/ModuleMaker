package com.library.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModelType : ViewModel> :
    AppCompatActivity() {

    protected lateinit var binding: ViewBinding


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): ViewModelType

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    abstract fun initObserver()


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initObserver()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(getBindingVariable(), getViewModel())
        binding.executePendingBindings()
    }


}