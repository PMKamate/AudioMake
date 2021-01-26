package com.demo.audiomake.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.audiomake.apiResponse.APIResult
import com.demo.audiomake.apiResponse.RSSFeedRepo
import com.demo.audiomake.datasourceimp.RSSDataSourceImp
import com.demo.audiomake.db.AppDatabase
import com.demo.audiomake.db.EntryDB
import com.demo.audiomake.db.mapDto
import com.demo.audiomake.ui.base.BaseViewModel
import com.demo.audiomake.ui.navigator.HomeNavigator
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
    var entryList = MutableLiveData<List<EntryDB>>()

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
        entryList.postValue(respository.getTopSongsFromDB())
    }

    private suspend fun getDatFromServer() {
        val response = respository.getTopSongs()
        withContext(Dispatchers.Main) {
            if (response.status == APIResult.Status.SUCCESS && response.data != null) {
                response.data.entryList?.map { it.mapDto() }?.let {
                    appDatabase.entryDao().insertEntry(
                        it
                    )
                    navigator?.updateSync(true)
                }
                getDatFromDB()
            } else {
                isProgressVisible.postValue(false)
                navigator?.updateSync(false)
                navigator?.showError("Error", response.message.toString())
            }
        }
    }
}


