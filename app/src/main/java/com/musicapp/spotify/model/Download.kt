package com.musicapp.spotify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class Download(
    @PrimaryKey
    val songId: String,
    val title: String,
    val artist: String,
    val filePath: String,
    val fileSize: Long,
    val downloadedDate: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false,
    val progress: Int = 0
)
