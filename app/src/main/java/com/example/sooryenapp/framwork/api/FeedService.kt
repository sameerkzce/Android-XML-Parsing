package com.example.sooryenapp.framwork.api

import com.example.sooryenapp.core.domain.Feed
import retrofit2.http.GET
/**service interface for API
 * */
public interface RSSFeedService {
    @GET("WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=20/xml")
    suspend fun getTopSongs(): Feed
}