package com.musicapp.spotify.database.dao

import androidx.room.*
import com.musicapp.spotify.model.Download
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(download: Download)

    @Update
    suspend fun updateDownload(download: Download)

    @Delete
    suspend fun deleteDownload(download: Download)

    @Query("SELECT * FROM downloads WHERE isCompleted = 1 ORDER BY downloadedDate DESC")
    fun getCompletedDownloads(): Flow<List<Download>>

    @Query("SELECT * FROM downloads WHERE isCompleted = 0")
    fun getDownloadsInProgress(): Flow<List<Download>>

    @Query("SELECT * FROM downloads WHERE songId = :songId")
    suspend fun getDownloadBySongId(songId: String): Download?

    @Query("DELETE FROM downloads WHERE songId = :songId")
    suspend fun removeDownload(songId: String)
}
