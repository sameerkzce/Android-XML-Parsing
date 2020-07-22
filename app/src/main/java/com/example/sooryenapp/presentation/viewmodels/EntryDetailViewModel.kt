package com.example.sooryenapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.sooryenapp.dto.EntryDto
import com.example.sooryenapp.presentation.base.BaseViewModel
/**viewmodel for Detail screen
 * */
class EntryDetailViewModel(application: Application) :  BaseViewModel(application) {
    val TAG: String = HomeViewModel::class.java.simpleName
    var entryLiveData = MutableLiveData<EntryDto>()

}