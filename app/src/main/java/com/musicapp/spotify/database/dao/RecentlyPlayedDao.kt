package com.musicapp.spotify.database.dao

import androidx.room.*
import com.musicapp.spotify.model.RecentlyPlayed
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyPlayedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecentlyPlayed(song: RecentlyPlayed)

    @Query("SELECT * FROM recently_played ORDER BY playedDate DESC LIMIT :limit")
    fun getRecentlyPlayed(limit: Int = 50): Flow<List<RecentlyPlayed>>

    @Query("DELETE FROM recently_played")
    suspend fun clearHistory()

    @Query("DELETE FROM recently_played WHERE playedDate < :timestamp")
    suspend fun deleteOlderThan(timestamp: Long)
}
