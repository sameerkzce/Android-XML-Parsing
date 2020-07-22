package com.example.sooryenapp.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sooryenapp.core.data.RSSFeedRepo
import com.example.sooryenapp.core.domain.APIResult
import com.example.sooryenapp.dto.EntryDto
import com.example.sooryenapp.dto.mapDto
import com.example.sooryenapp.framwork.database.AppDatabase
import com.example.sooryenapp.framwork.datasourceimp.RSSDataSourceImp
import com.example.sooryenapp.presentation.base.BaseViewModel
import com.example.sooryenapp.presentation.navigator.HomeNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
/**View model class for home screen
 * */
class HomeViewModel(application: Application) : BaseViewModel(application) {
    val TAG: String = HomeViewModel::class.java.simpleName
    private val appDatabase = AppDatabase.getDatabase(application)
    private var respository = RSSFeedRepo(RSSDataSourceImp(appDatabase))
    var navigator: HomeNavigator? = null
    var entryList = MutableLiveData<List<EntryDto>>()

    fun getTopSongs(isSyncDone: Boolean = false) {
        isProgressVisible.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {

            if (isSyncDone) {
                getDatFromDB()
            } else {
                getDatFromServer()
            }
        }
    }

    private suspend fun getDatFromDB() {
        Log.d(TAG, "getDatFromDB")
        entryList.postValue(respository.getTopSongsFromDB())
    }

    private suspend fun getDatFromServer() {

        val response = respository.getTopSongs()
        withContext(Dispatchers.Main) {
            isProgressVisible.postValue(false)
            if (response.status == APIResult.Status.SUCCESS && response.data != null) {
                response.data.entryList?.map { it.mapDto() }?.let {
                    appDatabase.entryDao().insertEntry(
                        it
                    )
                    navigator?.updateSync(true)
                }
                getDatFromDB()
                Log.d(TAG, "we got response")
            } else {
                navigator?.updateSync(false)
                Log.e(TAG, "we got Error${response.message.toString()}")
                navigator?.showError("Error", response.message.toString())
            }
        }
    }
}


