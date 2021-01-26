package com.demo.audiomake.interfaces

import com.demo.audiomake.apiResponse.APIResult
import com.demo.audiomake.apiResponse.Feed
import com.demo.audiomake.db.EntryDB



interface RSSFeedDataSource {
    suspend fun getTopSongs(): APIResult<Feed>
    suspend fun getTopSongsFromDB(): List<EntryDB>
}