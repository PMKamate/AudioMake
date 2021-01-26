package com.demo.audiomake.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.demo.audiomake.db.EntryDB
import com.demo.audiomake.ui.base.BaseViewModel

/**viewmodel for Detail screen
 * */
class EntryDetailViewModel(application: Application) :  BaseViewModel(application) {
    val TAG: String = HomeViewModel::class.java.simpleName
    var entryLiveData = MutableLiveData<EntryDB>()

}