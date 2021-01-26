package com.demo.audiomake.datasourceimp

import com.demo.audiomake.api.RetrofitInstance
import com.demo.audiomake.api.RetrofitService
import com.demo.audiomake.apiResponse.APIResult
import com.demo.audiomake.apiResponse.Feed
import com.demo.audiomake.db.AppDatabase
import com.demo.audiomake.db.EntryDB
import com.demo.audiomake.interfaces.RSSFeedDataSource


/**datasource implementation
 * */
class RSSDataSourceImp(private val appDatabase: AppDatabase) : RSSFeedDataSource {
    private var rssFeedService: RetrofitService = RetrofitInstance.retrofitService

    override suspend fun getTopSongs(): APIResult<Feed> {
        return try {
            val response = rssFeedService.getTopSongs()
            APIResult(APIResult.Status.SUCCESS, response, "")
        } catch (e: Exception) {
            APIResult(APIResult.Status.ERROR, null, e.message)
        }
    }

    override suspend fun getTopSongsFromDB(): List<EntryDB> {
        return appDatabase.entryDao().getAllEntry()
    }

}