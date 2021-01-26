package com.demo.audiomake.apiResponse

import com.demo.audiomake.interfaces.RSSFeedDataSource

/**repository to get data
 * */
class RSSFeedRepo(private val dataSource: RSSFeedDataSource) {
    suspend fun getTopSongs() = dataSource.getTopSongs()
    suspend fun getTopSongsFromDB() = dataSource.getTopSongsFromDB()
}