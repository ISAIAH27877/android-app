package com.musicapp.spotify.repository

import com.musicapp.spotify.database.MusicDatabase
import com.musicapp.spotify.model.Download
import kotlinx.coroutines.flow.Flow

class DownloadRepository(private val database: MusicDatabase) {
    private val downloadDao = database.downloadDao()

    fun getCompletedDownloads(): Flow<List<Download>> = downloadDao.getCompletedDownloads()

    fun getDownloadsInProgress(): Flow<List<Download>> = downloadDao.getDownloadsInProgress()

    suspend fun startDownload(download: Download) {
        downloadDao.insertDownload(download)
    }

    suspend fun updateDownloadProgress(songId: String, progress: Int) {
        val download = downloadDao.getDownloadBySongId(songId)
        download?.let {
            downloadDao.updateDownload(it.copy(progress = progress))
        }
    }

    suspend fun completeDownload(songId: String) {
        val download = downloadDao.getDownloadBySongId(songId)
        download?.let {
            downloadDao.updateDownload(it.copy(isCompleted = true, progress = 100))
        }
    }

    suspend fun removeDownload(songId: String) {
        downloadDao.removeDownload(songId)
    }
}
