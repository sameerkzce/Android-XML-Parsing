package com.example.sooryenapp.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**base class viewModel class all viewModels
 * */
open class BaseViewModel(application: Application) : AndroidViewModel(application){
    public val  isProgressVisible = MutableLiveData<Boolean>();
    init {
        isProgressVisible.value=false
    }
}