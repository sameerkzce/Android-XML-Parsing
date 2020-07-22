package com.example.sooryenapp.core.data

/**repository to get data
 * */
class RSSFeedRepo(private val dataSource: RSSFeedDataSource) {
    suspend fun getTopSongs() = dataSource.getTopSongs()
    suspend fun getTopSongsFromDB() = dataSource.getTopSongsFromDB()
}