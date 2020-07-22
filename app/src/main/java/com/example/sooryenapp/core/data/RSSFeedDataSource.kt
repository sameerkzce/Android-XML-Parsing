package com.example.sooryenapp.core.data

import com.example.sooryenapp.core.domain.APIResult
import com.example.sooryenapp.core.domain.Feed
import com.example.sooryenapp.dto.EntryDto

/**interface for datasource which will be implemented in framework
 * */
interface RSSFeedDataSource {
    suspend fun getTopSongs(): APIResult<Feed>
    suspend fun getTopSongsFromDB(): List<EntryDto>
}