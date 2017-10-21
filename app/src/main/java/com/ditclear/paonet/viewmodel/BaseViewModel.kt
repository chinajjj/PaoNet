package com.ditclear.paonet.viewmodel

import android.databinding.BaseObservable
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * 页面描述：viewModel 基类
 *
 * Created by ditclear on 2017/9/28.
 */

open class BaseViewModel : BaseObservable(){



    var lifecycle : LifecycleTransformer<*> ?=null

    fun <T> bindToLifecycle() :LifecycleTransformer<T> = lifecycle!! as LifecycleTransformer<T>


}