package com.demo.audiomake.api

import com.demo.audiomake.apiResponse.Feed
import retrofit2.http.GET
/**service interface for API
 * */
public interface RetrofitService {
    @GET("WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=20/xml")
    suspend fun getTopSongs(): Feed
}