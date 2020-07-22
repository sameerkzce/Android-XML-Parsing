package com.example.sooryenapp.framwork.datasourceimp

import com.example.sooryenapp.core.data.RSSFeedDataSource
import com.example.sooryenapp.core.domain.APIResult
import com.example.sooryenapp.core.domain.Feed
import com.example.sooryenapp.dto.EntryDto
import com.example.sooryenapp.framwork.api.RSSFeedService
import com.example.sooryenapp.framwork.api.RetrofitInstance
import com.example.sooryenapp.framwork.database.AppDatabase
/**datasource implementation
 * */
class RSSDataSourceImp(private val appDatabase: AppDatabase) : RSSFeedDataSource {
    private var rssFeedService: RSSFeedService = RetrofitInstance.feedService

    override suspend fun getTopSongs(): APIResult<Feed> {
        return try {
            val response = rssFeedService.getTopSongs()
            APIResult(APIResult.Status.SUCCESS, response, "")
        } catch (e: Exception) {
            APIResult(APIResult.Status.ERROR, null, e.message)
        }
    }

    override suspend fun getTopSongsFromDB(): List<EntryDto> {
        return appDatabase.entryDao().getAllEntry()
    }

}